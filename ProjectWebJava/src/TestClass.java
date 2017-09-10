import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Test;

public class TestClass {

	@SuppressWarnings("deprecation")
	@Test
	public void testAddExistingBook()
	{
		BookHandler bookHandler = new BookHandler();
		Book book = new Book();
		book.setTitle("Mastering åäö");
		book.setAuthor("Average Swede");
		book.setPrice(new BigDecimal(762.00));
		boolean expectedOutput = true;
		boolean actualOutput = bookHandler.add(book, 1);
		assertEquals(expectedOutput,actualOutput);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testBuyBook()
	{
		BookHandler bookHandler = new BookHandler();
		ArrayList<Book> bookArray = new ArrayList<Book>();
		Book book = new Book();
		Book[] books = new Book[2];
		book.setTitle("Mastering d");
		book.setAuthor("Average Swede");
		book.setPrice(new BigDecimal(762.00));
		books[0]=book;
		book = new Book();
		book.setTitle("Mastering B");
		book.setAuthor("Average Swede");
		book.setPrice(new BigDecimal(762.00));
		books[1]=book;
		int[] expectedOutput = new int[2];
		expectedOutput[0] = 2;
		expectedOutput[1] = 2;
		int[] actualOutput = bookHandler.buy(books);
		assertArrayEquals(expectedOutput, actualOutput);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testRemoveExistingBook()
	{
		BookHandler bookHandler = new BookHandler();
		ArrayList<Book> bookArray = new ArrayList<Book>();
		Book book = new Book();
		book.setTitle("Mastering äö");
		book.setAuthor("Average Swede");
		book.setPrice(new BigDecimal(762.00));
		boolean expectedOutput = false;
		boolean actualOutput = bookHandler.removeBook(book);
		assertEquals(expectedOutput, actualOutput);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testRemoveNonExistingBook()
	{
		BookHandler bookHandler = new BookHandler();
		ArrayList<Book> bookArray = new ArrayList<Book>();
		Book book = new Book();
		book.setTitle("Mastering AB");
		book.setAuthor("Average Swede");
		book.setPrice(new BigDecimal(762.00));
		boolean expectedOutput = false;
		boolean actualOutput = bookHandler.removeBook(book);
		assertEquals(expectedOutput, actualOutput);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testAddNonExistingBook()
	{
		BookHandler bookHandler = new BookHandler();
		Book book = new Book();
		book.setTitle("Mastering BBO");
		book.setAuthor("Average Swede");
		book.setPrice(new BigDecimal(762.00));
		boolean expectedOutput = true;
		boolean actualOutput = bookHandler.add(book, 1);
		assertEquals(expectedOutput,actualOutput);
	}


}
