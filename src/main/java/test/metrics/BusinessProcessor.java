package test.metrics;

import java.io.File;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Counter;
import com.codahale.metrics.CsvReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

public class BusinessProcessor {

	final static MetricRegistry metrics = new MetricRegistry();

	static {
		final ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics).convertRatesTo(TimeUnit.SECONDS)
				.convertDurationsTo(TimeUnit.MILLISECONDS).build();
//		reporter.start(20, TimeUnit.SECONDS);

		final CsvReporter csvreporter = CsvReporter.forRegistry(metrics).formatFor(Locale.FRENCH).convertRatesTo(TimeUnit.SECONDS)
				.convertDurationsTo(TimeUnit.MILLISECONDS).build(new File("\\\\D:\\Travail\\Projet\\MetricsTest\\metrics"));
		csvreporter.start(1, TimeUnit.SECONDS);
	}

	final Meter requests = metrics.meter(MetricRegistry.name(BusinessProcessor.class, "requests"));
	private final Counter requestNb = metrics.counter(MetricRegistry.name(BusinessProcessor.class, "requestNumber"));
	private final Timer responses = metrics.timer(MetricRegistry.name(BusinessProcessor.class, "responses"));

	public void process() throws InterruptedException {
		requestNb.inc();
		requests.mark();
		final Timer.Context context = responses.time();

		double random = Math.random();
		double d = random * 2000;
		System.out.println("Processing request, sleeping " + d + " ms");
		Thread.sleep((long) d);

		context.stop();
	}

}
