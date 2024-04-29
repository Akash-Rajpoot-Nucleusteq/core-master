package in.nucleusteq.plasma.feignservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import feign.Contract;
import feign.Feign;
import feign.Retryer;
import feign.hystrix.HystrixFeign;

@Configuration
public class TokenClientConfiguration {

		/**
		 * Max attempts for hitting another microservice end point.
		 */
		public static final int MAX_ATTEMPTS = 5;
		/**
		 * Initial interval millis.
		 */
		public static final int INITIAL_INTERVAL_MILLIS = 1000;
		/**
		 * Max interval millis.
		 */
		public static final int MAX_INTERVAL_MILLIS = 10000;
		/**
		 * This method defines a bean for creating a Feign Builder with Hystrix
		 * integration. Feign Builder is responsible for creating Feign clients, and by
		 * using HystrixFeign.builder().
		 *
		 * @return feign builder
		 */
		@Bean
		@Scope("prototype")
		public Feign.Builder actFeignBuilder() {
			return HystrixFeign.builder();
		}
		/**
		 * This method defines a bean for configuring the Retryer used by Feign. Retryer
		 * is responsible for determining the retry behavior for failed HTTP requests.
		 *
		 * @return Retryer
		 */
		@Bean
		@Scope("prototype")
		public Retryer actRetryer() {
			return new Retryer.Default(MAX_INTERVAL_MILLIS, INITIAL_INTERVAL_MILLIS, MAX_ATTEMPTS);
		}
		/**
		 * This method defines a bean for customizing the contract used by Feign to
		 * interpret annotations.
		 *
		 * @return Contract
		 */
		@Bean
		public Contract useFeignAnnotations() {
			return new Contract.Default();
		}
	}

