package com.example.demo.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ResourceLoader;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlowBuilder;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.jdbc.JdbcPollingChannelAdapter;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.scheduling.support.PeriodicTrigger;

import javax.sql.DataSource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@EnableBinding(Source.class)
@EnableConfigurationProperties({JdbcSourceProperties.class})
public class StagingTransactionEmitter {
    @Autowired
    private JdbcSourceProperties properties;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private Source source;

    @Bean
    public MessageSource<Object> jdbcMessageSource() {
        JdbcPollingChannelAdapter jdbcPollingChannelAdapter =
                new JdbcPollingChannelAdapter(this.dataSource, this.properties.getQuery());
        jdbcPollingChannelAdapter.setUpdateSql(this.properties.getUpdate());
        return jdbcPollingChannelAdapter;
    }

    @Bean
    public IntegrationFlow pollingFlow() {
        IntegrationFlowBuilder flowBuilder = IntegrationFlows.from(jdbcMessageSource());
        flowBuilder.channel(this.source.output());
        return flowBuilder.get();
    }

    @Bean(
            name = {"defaultPoller", "org.springframework.integration.context.defaultPollerMetadata"}
    )
    public PollerMetadata defaultPoller() {
        PollerMetadata pollerMetadata = new PollerMetadata();
        PeriodicTrigger trigger = new PeriodicTrigger(this.properties.getTriggerDelay(), TimeUnit.SECONDS);
        pollerMetadata.setTrigger(trigger);
        pollerMetadata.setMaxMessagesPerPoll(1L);
        return pollerMetadata;
    }


}
