package com.galendar.global.crawler.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "crawling.contest-korea")
@Getter
@Setter
public class ContestKoreaConfig {

    private String baseUrl;
    private String subjectUrl;
    private String itListUrl;
    private int daysToSearch;
    public String getSubjectUrl(){
        return baseUrl + subjectUrl;
    }

    public String getItListUrl() {
        return getSubjectUrl() + itListUrl;
    }

}
