package net.gfu.seminar.spring.batch.job.step.tasklet.chunk;

import net.gfu.seminar.spring.batch.model.Product;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class FileReader implements ItemReader<Product> {

	protected static int MAX_ITEMS = 100;

	private int readItems = 0;

	protected Log log = LogFactory.getLog(FileReader.class);

	@Override
	public Product read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {
		log.debug("FileReader called");

		if (readItems < MAX_ITEMS) {
			++readItems;
			return new Product();
		}
		return null;

	}

}
