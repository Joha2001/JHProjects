public class Cow {
	protected String name; //String holds the name of the cow
	protected String image;//String holds the image of the cow
	public Cow(String name){//Creates a cow object with a specific name
		this.name = name;
		image = "";
	}
	public String getName(){//Returns the name of the cow
		return name;
	}
	public String getImage(){//Returns the image of the cow
		return image;
	}
	public void setImage(String _image){//Sets the image of the cow to the given String.
		image = _image;
	}
}
