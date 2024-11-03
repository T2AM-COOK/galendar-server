package com.galendar.global.crawler;

import com.galendar.global.crawler.dto.ContestCrawlerDTO;
import com.galendar.global.crawler.dto.ContestDetailCrawlerDTO;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

public interface ContestCrawler {
    List<ContestCrawlerDTO> list() throws IOException;
    Document view(String url) throws IOException;
    public String extractViewTitle(Document document);
    String extractViewContent(Document document);
    String extractViewImageUrl(Document document);
    ContestDetailCrawlerDTO parseContestDetails(Document document);

}
