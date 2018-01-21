package info.doula;

import org.springframework.boot.context.properties.ConfigurationProperties;

import static info.doula.ApplicationProperties.CONFIGURATION_PROPERTY_PREFIX;

/**
 * Mohammed Hossain Doula
 *
 * @hossaindoula | @itconquest
 * <p>
 * http://hossaindoula.com
 * <p>
 * https://github.com/hossaindoula
 */
@ConfigurationProperties(prefix = CONFIGURATION_PROPERTY_PREFIX, ignoreUnknownFields = false)
public class ApplicationProperties {

    static final String CONFIGURATION_PROPERTY_PREFIX = "application";
    private final Async async = new Async();

    public Async getAsync() {
        return async;
    }

    public static class Async {

        private Integer corePoolSize;
        private Integer maxPoolSize;
        private Integer queueCapacity;

        public Integer getCorePoolSize() {
            return corePoolSize;
        }

        public void setCorePoolSize(final Integer corePoolSize) {
            this.corePoolSize = corePoolSize;
        }

        public Integer getMaxPoolSize() {
            return maxPoolSize;
        }

        public void setMaxPoolSize(final Integer maxPoolSize) {
            this.maxPoolSize = maxPoolSize;
        }

        public Integer getQueueCapacity() {
            return queueCapacity;
        }

        public void setQueueCapacity(final Integer queueCapacity) {
            this.queueCapacity = queueCapacity;
        }
    }
}
