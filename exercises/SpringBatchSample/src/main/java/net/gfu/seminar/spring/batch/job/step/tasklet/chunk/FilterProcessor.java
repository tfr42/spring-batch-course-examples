package net.gfu.seminar.spring.batch.job.step.tasklet.chunk;

import net.gfu.seminar.spring.batch.model.Product;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemProcessor;

public class FilterProcessor implements ItemProcessor<Product, Product> {

	protected Log log = LogFactory.getLog(FilterProcessor.class);

	@Override
	public Product process(Product item) throws Exception {

		log.debug("ProductProcessor called");

		return item;
	}

}
