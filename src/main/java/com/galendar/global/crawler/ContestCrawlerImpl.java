package com.galendar.global.crawler;

import com.galendar.global.crawler.config.ContestKoreaConfig;
import com.galendar.global.crawler.dto.ContestCrawlerDTO;
import com.galendar.global.crawler.dto.ContestDetailCrawlerDTO;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class ContestCrawlerImpl implements ContestCrawler {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter DATE_RANGE_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/128.0.0.0 Safari/537.36";

    private final ContestKoreaConfig contestKoreaConfig;

    public List<ContestCrawlerDTO> list() throws IOException {
        List<ContestCrawlerDTO> results = new ArrayList<>();
        LocalDate cutoffDate = LocalDate.now().minusDays(contestKoreaConfig.getDaysToSearch());
        Document document = fetchDocument(contestKoreaConfig.getItListUrl());
        Element listStyle2Element = document.selectFirst(".list_style_2 ul");
        if (listStyle2Element != null) {
            for (Element li : listStyle2Element.children()) {
                String viewLink = extractViewLink(li);
                if (viewLink.isEmpty()) continue;
                String strNo = extractStrNoParam(viewLink);
                if (strNo.equals("")) continue;
                LocalDate contestRegDate = extractContestRegDate(strNo);
                if (contestRegDate.isAfter(cutoffDate)) {
                    results.add(new ContestCrawlerDTO(contestKoreaConfig.getSubjectUrl() + viewLink, strNo));
                }
            }
        }
        return results;
    }

    public Document view(String url) throws IOException {
        return fetchDocument(url);
    }

    private Document fetchDocument(String url) throws IOException {
        return Jsoup.connect(url)
                .userAgent(USER_AGENT)
                .get();
    }

    private String extractViewLink(Element li) {
        Element element = li.selectFirst("a");
        return element != null ? element.attr("href") : "";
    }

    public String extractStrNoParam(String params) {
        return Arrays.stream(params.split("&"))
                .filter(param -> param.startsWith("str_no=")).findFirst()
                .map(str -> str.substring(7, str.length()))
                .orElse("");
    }

    private LocalDate extractContestRegDate(String date) {
        return LocalDate.parse(date.substring(0, 8), FORMATTER);
    }

    private String viewUrl(String viewUrl) {
        return contestKoreaConfig.getSubjectUrl() + viewUrl;
    }

    public String extractViewTitle(Document document) {
        Element titleElement = document.selectFirst("div.view_top_area.clfx h1");
        return titleElement != null ? titleElement.text() : "";
    }

    public String extractViewContent(Document document) {
        StringBuilder content = new StringBuilder();
        Elements textElements = document.selectFirst("div.view_detail_area .txt").select("h2, p");
        for (Element element : textElements) {
            content.append(element.text()).append("\n");
        }
        return content.toString();
    }

    public String extractViewImageUrl(Document document) {
        Element imgElement = document.selectFirst("div.view_detail_area .img_area img");
        return imgElement != null ? contestKoreaConfig.getBaseUrl() + imgElement.attr("src") : "";
    }

    public ContestDetailCrawlerDTO parseContestDetails(Document document) {
        Elements txtAreaElements = document.selectFirst("div.txt_area").select("tr");
        ContestDetailCrawlerDTO contestDetailCrawlerDTO = new ContestDetailCrawlerDTO();
        for (Element element : txtAreaElements) {
            String th = element.select("th").text();
            String td = element.select("td").text();
            switch (th) {
                case "참가대상":
                    contestDetailCrawlerDTO.setTargets(parseContestTarget(td));
                    break;
                case "대회지역":
                    contestDetailCrawlerDTO.setRegions(parseContestLocation(td));
                    break;
                case "참가비용":
                    contestDetailCrawlerDTO.setCost(td);
                    break;
                case "접수기간":
                    setDateRange(td, contestDetailCrawlerDTO::setSubmitStartDate, contestDetailCrawlerDTO::setSubmitEndDate);
                    break;
                case "심사기간":
                    setDateRange(td, contestDetailCrawlerDTO::setContestStartDate, contestDetailCrawlerDTO::setContestEndDate);
                    break;
                default:
                    break;
            }
        }
        return contestDetailCrawlerDTO;
    }

    private String parseContestTarget(String target) {
        return target.split("▶")[0].replace(" ", "");
    }

    private String parseContestLocation(String location) {
        return location.replace(" ", "");
    }
//
//    private String parseCost(String cost) {
//        return cost.equals("무료 접수") ? ContestCost.FREE : ContestCost.PAID;
//    }

    private void setDateRange(String dateRange, Consumer<LocalDate> startSetter, Consumer<LocalDate> endSetter) {
        List<LocalDate> dates = Arrays.stream(dateRange.split("~"))
                .map(date -> LocalDate.parse(date.trim(), DATE_RANGE_FORMATTER))
                .toList();
        if (dates.size() >= 2) {
            startSetter.accept(dates.get(0));
            endSetter.accept(dates.get(1));
        }
    }

}
