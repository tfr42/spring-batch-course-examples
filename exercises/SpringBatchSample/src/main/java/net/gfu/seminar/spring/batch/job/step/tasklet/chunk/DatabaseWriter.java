package net.gfu.seminar.spring.batch.job.step.tasklet.chunk;


import net.gfu.seminar.spring.batch.model.Product;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class DatabaseWriter implements ItemWriter<Product> {

	protected Log log = LogFactory.getLog(DatabaseWriter.class);

	@Override
	public void write(List<? extends Product> items) throws Exception {

		log.debug("FileWriter called");

	}

}
