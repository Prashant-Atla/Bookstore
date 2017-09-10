import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Handler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;


public class BookHandler implements BookList{
	
	static HashMap<Book,Integer> bookMap = new HashMap<Book,Integer>();
	static HashMap<Book,Integer> userBasket = new HashMap<Book,Integer>();

	public BookHandler()
	{
		bookMap = new HashMap<Book,Integer>();
		userBasket = new HashMap<Book,Integer>();
		try
		{
		 // Make a URL to the web page
	    URL url = new URL("https://raw.githubusercontent.com/contribe/contribe/dev/bookstoredata/bookstoredata.txt");

	    // Get the input stream through URL Connection
	    URLConnection con = url.openConnection();
	    InputStream is =con.getInputStream();

	    BufferedReader br = new BufferedReader(new InputStreamReader(is));
	    String line = null;
	    String components[];
	    int quantity;
	    // read each line and write to System.out
	    while ((line = br.readLine()) != null) {
	        components = line.split(";");
	        Book book = new Book();
	        book.setTitle(components[0]);
	        book.setAuthor(components[1]);
	        book.setPrice(new BigDecimal(components[2].replace(",", "")));
	        quantity = Integer.parseInt(components[3]);
	        bookMap.put(book, quantity);
	    }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	    
	}
	
    


	public static void main(String[] args) throws IOException{
		BookHandler bookHandle = new BookHandler();
		System.out.println("Hello!! Welcome to the BookStore");
        String key ="";
		System.out.println("Enter your choice -");
		System.out.println("A for Add to existing stock ");
		System.out.println("B for Buy a book");
		System.out.println("D for Display User basket");
		System.out.println("L for List books");
		System.out.println("E for exit");
		
		Scanner sc=new Scanner(System.in);
		
		key = sc.nextLine();
		while(!key.equalsIgnoreCase("E")){
			if(key.equalsIgnoreCase("A") || key.equalsIgnoreCase("B") || key.equalsIgnoreCase("D") || key.equalsIgnoreCase("L") || key.equalsIgnoreCase("R"))
			{
			handleInput(key,bookHandle);
			}
			System.out.println("Enter your choice -");
			System.out.println("A for add book to store");
			System.out.println("B for buy a book / add book to basket");
			System.out.println("D for Display user basket ");
			System.out.println("L for list books");
			System.out.println("R for remove book from basket");
			System.out.println("E for exit");
			key = sc.nextLine();
		}
		
	}
	

	private static void handleInput(String key,BookHandler bookHandle) {
		// TODO Auto-generated method stub
		
		if(key.equalsIgnoreCase("L"))
		{
		System.out.println("Enter book or author name for search or press ENTER");
		Scanner sc = new Scanner(System.in);
		String choice = sc.nextLine();
		Book books[]= bookHandle.list(choice);	
		if(books[0].getTitle().equalsIgnoreCase("No Books"))
		{
		System.out.println("Sorry. No books matching that description exist");	
		}
		else
		{
			System.out.println("The books present are:");
			for(int i=0;i<books.length;i++)
			{
			for(Book book : bookMap.keySet())
			{
				if(book.getTitle().equalsIgnoreCase(books[i].getTitle()))
				{
					if(book.getAuthor().equalsIgnoreCase(books[i].getAuthor()))
					{
						if(book.getPrice()==(books[i].getPrice()))
								{
							System.out.println("Book: "+books[i].getTitle()+" Author: "+books[i].getAuthor()+" Price: "+books[i].getPrice()+" Qty: "+bookMap.get(book));
								}
					}
				}
			}
			}
		
		}
		}
		else if(key.equalsIgnoreCase("A")){
			System.out.println("Enter the name of book");
			Scanner sc = new Scanner(System.in);
			String bookName = sc.nextLine();
			System.out.println("Enter the author of book");
			String bookAuthor = sc.nextLine();
			System.out.println("Enter the price of book");
			String price = sc.nextLine();
			BigDecimal bookPrice;
			if(price.contains(","))
			{
			 bookPrice = new BigDecimal(price.replace(",", ""));
			}
			else
			{
			 bookPrice = new BigDecimal(price);	
			}
			System.out.println("Enter the qty of book");
			String qty = sc.nextLine();
			int bookQty = Integer.parseInt(qty);
			Book enteredBook = new Book();
			enteredBook.setTitle(bookName);
			enteredBook.setAuthor(bookAuthor);
			enteredBook.setPrice(bookPrice);
			boolean addition = bookHandle.add(enteredBook, bookQty);
			if(addition)
			{
				System.out.println("Book Succesfully added");
			}
			else
			{
				System.out.println("Book could not be added. Try again");
			}
		}
		else if(key.equalsIgnoreCase("B"))
		{
			ArrayList<Book> purchaseList = new ArrayList<Book>();
			Book enteredBook = new Book();
			System.out.println("Enter the name of book for purchase");
			Scanner sc = new Scanner(System.in);
			String bookName = sc.nextLine();
			System.out.println("Enter the author of book");
			String bookAuthor = sc.nextLine();
			System.out.println("Enter the price of book");
			String price = sc.nextLine();
			BigDecimal bookPrice;
			if(price.contains(","))
			{
			 bookPrice = new BigDecimal(price.replace(",", ""));
			}
			else
			{
			 bookPrice = new BigDecimal(price);	
			}
			enteredBook.setTitle(bookName);
			enteredBook.setAuthor(bookAuthor);
			enteredBook.setPrice(bookPrice);
			purchaseList.add(enteredBook);
		   System.out.println("Do you want to purchase another book? Y/N");
		   key = sc.nextLine();
		   while(!key.equalsIgnoreCase("N"))
		   {
			   System.out.println("Enter the name of book for purchase");
				 bookName = sc.nextLine();
				System.out.println("Enter the author of book");
				 bookAuthor = sc.nextLine();
				System.out.println("Enter the price of book");
				 price = sc.nextLine();
				if(price.contains(","))
				{
				 bookPrice = new BigDecimal(price.replace(",", ""));
				}
				else
				{
				 bookPrice = new BigDecimal(price);	
				}
				enteredBook = new Book();
				enteredBook.setTitle(bookName);
				enteredBook.setAuthor(bookAuthor);
				enteredBook.setPrice(bookPrice);
				purchaseList.add(enteredBook);
		   System.out.println("Do you want to purchase another book? Y/N");  
		   key = sc.nextLine();
		   }
	    Book[] purchaseArray = new Book[purchaseList.size()];
	    purchaseList.toArray(purchaseArray);
		int[] statusArray = bookHandle.buy(purchaseArray);
		System.out.println("Status of Purchase of Books");
		for(int i=0;i<statusArray.length;i++)
		{
		   System.out.print("Book: "+purchaseArray[i].getTitle()+" Author: "+purchaseArray[i].getAuthor()+" Price: "+purchaseArray[i].getPrice()+" Status: ");
		   if(statusArray[i]==0)
		   {
			   System.out.println("OK(0)");   
		   }
		   else if(statusArray[i]==1)
		   {
			   System.out.println("NOT_IN_STOCK(1)"); 
		   }
		   else if(statusArray[i]==2)
		   {
			   System.out.println("DOES_NOT_EXIST(2)"); 
		   }
			   
		}
		
		}
		else if(key.equalsIgnoreCase("D"))
		{
			Double totalPrice=0.00;
			if(userBasket.isEmpty())
			{
				System.out.println("There are no items to display in the User basket");
			}
			else
			{
				for(Book book:userBasket.keySet())
				{
					totalPrice= totalPrice + Double.parseDouble(book.getPrice().toString())*userBasket.get(book);
					System.out.println("Book: "+book.getTitle()+" Author: "+book.getAuthor()+" Price: "+book.getPrice()+" Qty: "+userBasket.get(book));
				}
				System.out.println("Total Price of Basket: "+totalPrice);
			}
		}
		else if(key.equalsIgnoreCase("R"))
		{
			Book enteredBook = new Book();
			System.out.println("Enter the name of book for removal");
			Scanner sc = new Scanner(System.in);
			String bookName = sc.nextLine();
			System.out.println("Enter the author of book");
			String bookAuthor = sc.nextLine();
			System.out.println("Enter the price of book");
			String price = sc.nextLine();
			BigDecimal bookPrice;
			if(price.contains(","))
			{
			 bookPrice = new BigDecimal(price.replace(",", ""));
			}
			else
			{
			 bookPrice = new BigDecimal(price);	
			}
			enteredBook.setTitle(bookName);
			enteredBook.setAuthor(bookAuthor);
			enteredBook.setPrice(bookPrice);
			boolean removal = bookHandle.removeBook(enteredBook);
			if(removal)
			{
				System.out.println("The book has been succesfully removed from basket");
			}
			else
			{
				System.out.println("The user basket does not have the book.Cannot be removed");
			}
		}
	}


	public boolean removeBook(Book book) {
		
		int currentQty = 0;
		boolean status = false;
		boolean addedToMap = false;
		for(Book bookx : userBasket.keySet())
		{
			if(book.getTitle().equalsIgnoreCase(bookx.getTitle()))
			{
				if(book.getAuthor().equalsIgnoreCase(bookx.getAuthor()))
				{
					if(book.getPrice().toString().equalsIgnoreCase(bookx.getPrice().toString()))
							{
						status = true;
						currentQty = userBasket.get(bookx);
						userBasket.put(bookx, currentQty-1);
						if(currentQty-1 == 0)
						{
							userBasket.remove(bookx);	
						}
							for(Book bookn : bookMap.keySet())
							{
							if(book.getTitle().equalsIgnoreCase(bookn.getTitle()))
							{
								if(book.getAuthor().equalsIgnoreCase(bookn.getAuthor()))
								{
									if(book.getPrice().toString().equalsIgnoreCase(bookn.getPrice().toString()))
											{
										bookMap.put(bookn,bookMap.get(bookn)+1); 
										addedToMap = true;
											}
								}
							}
							}
							if(!addedToMap)
							{
							bookMap.put(bookx,1);
							}
						
							}
				}
			}
		}
		return status;
		}


	@Override
	public Book[] list(String searchString) {
		// TODO Auto-generated method stub
	ArrayList<Book> bookList = new ArrayList<Book>();
	if(searchString.equalsIgnoreCase(""))
	{
		
		for(Book book : bookMap.keySet())
		{
			Book bookCurrent = book;
			bookList.add(book);
		}
		Book[] books = new Book[bookList.size()];
		bookList.toArray(books);
		return books;
	}
	else
	{
		for(Book book : bookMap.keySet())
		{
			if(book.getTitle().equalsIgnoreCase(searchString))
			{
				bookList.add(book);
			}
		}
		if(bookList.isEmpty())
		{
		for(Book book : bookMap.keySet())
	    {
		if(book.getAuthor().equalsIgnoreCase(searchString))
		{
			bookList.add(book);
		}
	    }
		}
	if(bookList.isEmpty())
	{
		Book bookEmpty = new Book();
		bookEmpty.setTitle("No Books");
		bookEmpty.setAuthor("No Books");
		bookEmpty.setPrice(new BigDecimal("0.00"));
		Book[] empty = new Book[1];
		empty[0]=bookEmpty;
		return empty;
	}
	else
	{
		Book[] books = new Book[bookList.size()];
		bookList.toArray(books);
		return books;
	}
	}
	
	}

	@Override
	public boolean add(Book book,int quantity) {
		// TODO Auto-generated method stub
		int currentQty=0;
		for(Book bookx : bookMap.keySet())
		{
			if(book.getTitle().equalsIgnoreCase(bookx.getTitle()))
			{
				if(book.getAuthor().equalsIgnoreCase(bookx.getAuthor()))
				{
					if(book.getPrice().toString().equalsIgnoreCase(bookx.getPrice().toString()))
							{
						currentQty = bookMap.get(bookx);
						bookMap.put(bookx, currentQty+quantity);
						return true;
							}
				}
			}
		}
	bookMap.put(book, quantity);
	return true;
			
	}

	@Override
	public int[] buy(Book... books) {
		// TODO Auto-generated method stub
		int currentQty=0;
		int[] statusArray;
		ArrayList<Integer> statuses = new ArrayList<Integer>();
		boolean added = false;
		boolean addedToBasket = false;
		for(int i=0;i<books.length;i++)
		{
		for(Book bookx : bookMap.keySet())
		{
			if(books[i].getTitle().equalsIgnoreCase(bookx.getTitle()))
			{
				if(books[i].getAuthor().equalsIgnoreCase(bookx.getAuthor()))
				{
					if(books[i].getPrice().toString().equalsIgnoreCase(bookx.getPrice().toString()))
							{
						currentQty = bookMap.get(bookx);
						if(currentQty==0)
						{
							statuses.add(1);
							added=true;
						}
						else
						{
							bookMap.put(bookx, currentQty-1);
							for(Book bookn : userBasket.keySet())
							{
							if(books[i].getTitle().equalsIgnoreCase(bookn.getTitle()))
							{
								if(books[i].getAuthor().equalsIgnoreCase(bookn.getAuthor()))
								{
									if(books[i].getPrice().toString().equalsIgnoreCase(bookn.getPrice().toString()))
											{
										userBasket.put(bookn,userBasket.get(bookn)+1); 
										addedToBasket = true;
											}
								}
							}
							}
							if(!addedToBasket)
							{
							userBasket.put(bookx,1);
							}
							addedToBasket = false;
							statuses.add(0);	
							added = true;
						}
						
							}
				}
			}
		}
		if(!added)
		{
		statuses.add(2);
		}
		else
		{
		added = false;	
		}
		}
		
	  statusArray = new int[statuses.size()];
	  for(int i=0;i<statuses.size();i++)
			{
				statusArray[i]=statuses.get(i);
			}
		
	 return statusArray;
	}

}
