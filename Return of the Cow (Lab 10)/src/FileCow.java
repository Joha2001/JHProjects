import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class FileCow extends Cow{
	public FileCow(String name, String filename){//FileCow constructor
	    super(name);//Uses the super constructor for the name
        try
        {

            Reader fileReader = new FileReader(filename);
            int i;
            while ((i=fileReader.read()) != -1)//Code learned and adapted from geeksforgeeks
                image += (char) i;//Image is created one character at a time.
        }
        catch (IOException e)//Catches the exception
        {
            System.out.println(e.getMessage());
            throw new RuntimeException("MOOOOO!!!!!!");
        }
    }

    public void setImage() { //Supposed to always throw the RuntimeException.
        throw new RuntimeException("Cannot reset FileCow Image");
    }
}

