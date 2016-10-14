package design.NetFlix;

/**
 * http://i.stack.imgur.com/hzVvV.png
 *
 */
public class User {

	private String name;
	private Sex sex;

	public enum Sex {
		man, woman;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public Sex getSex() {
		return this.sex;
	}

	public static void main(String[] args) {
		User user = new User();
		user.setSex(User.Sex.woman);
		System.out.println(user.getSex());
		
		while (true){
			;
		}

	}

}
