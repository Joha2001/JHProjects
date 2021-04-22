public class IceDragon extends Dragon{
	public IceDragon(String name, String image){//Constructor for IceDragon which extends Dragon (which also extends Cow)
		super(name, image);
	}
	@Override
	public boolean canBreatheFire(){//False for canBreathFire.
		return false;
	}
}
