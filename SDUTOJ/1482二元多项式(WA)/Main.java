import java.util.*;
class express
{
	int flower,xpow,ypow;
	public express(){}
	public express(int flower,int xpow,int ypow)
	{
		this.flower = flower;
		this.xpow 	= xpow;
		this.ypow 	= ypow;
	}
	public static void plus(LinkedList<express> a,LinkedList<express> b)
	{
		for(express plus : a)
		{
			int tag = 0;
			for(express subplus : b)
			{
				if(subplus.xpow == plus.xpow && subplus.ypow == plus.ypow){
					subplus.flower += plus.flower;
					tag = 1;
				}
			}
			if(tag == 0)
			{
				b.add(new express(plus.flower,plus.xpow,plus.ypow));
			}
		}
	}
	public static LinkedList<express> product(LinkedList<express> a,LinkedList<express> b)
	{
		LinkedList<express> add = new LinkedList();
		for(express plus : a)
		{
			int tag = 0;
			for(express subplus : b)
			{
				express ad = new express();
				ad.flower = subplus.flower * plus.flower;
				ad.xpow = subplus.xpow + plus.xpow;
				ad.ypow = subplus.ypow + plus.ypow;
				add.add(ad);
			}
		}
		return add;
	}
	public void printExpress()
	{
		if(flower > 1){
			System.out.printf("%d",this.flower);
		}
		else if(this.flower > 0 && this.xpow == 0 && this.ypow == 0)
		{
			System.out.printf("%d",this.flower);
			return ;
		}
		if(xpow > 0)
		{
			System.out.printf("x");
			if(xpow > 1){
				System.out.printf("^%d",this.xpow);
			}
		}
		if(ypow > 0)
		{
			System.out.printf("y");
			if(ypow > 1){
				System.out.printf("^%d",this.ypow);
			}
		}
		return ;
	}
	public  express (String str)
	{
		char[] conl = str.toCharArray();
		String num = new String();
		int flowerOrPow = 0;
		this.flower = 0;
		this.xpow 	= 0;
		this.ypow 	= 0;
		for (char c : conl)
		{
			if(c >= '0' && c <= '9')
			{
				num += Character.toString(c);
			}
			else 
			{
				if(flowerOrPow == 0){
					if(num.equals("") == false){
						this.flower = Integer.valueOf(num);
					}
					flowerOrPow = 3;
				}
				else if (flowerOrPow == 3){
					if(num.equals("") == false){
						this.flower = (int)Math.pow((double)this.flower,(double)Integer.valueOf(num));
					}
				}
				else if (flowerOrPow == 1){
					if(num.equals("") == false){
						this.xpow = Integer.valueOf(num);
					}
				}
				else{
					if(num.equals("") == false){
						this.ypow = Integer.valueOf(num);
					}
				}
				num = new String();
				num += "";
				if (c == 'x'){
					this.xpow = 1;
					flowerOrPow = 1;
				}
				else if (c == 'y'){
					this.ypow = 1;
					flowerOrPow = 2;
				}
			}

		}
		if(flowerOrPow == 0){
			if(num.equals("") == false){
				this.flower = Integer.valueOf(num);
			}

		}
		else if (flowerOrPow == 3){
			if(num.equals("") == false){
				this.flower = (int)Math.pow((double)this.flower,(double)Integer.valueOf(num));
			}
		}
		else if (flowerOrPow == 1){
			if(num.equals("") == false){
				this.xpow = Integer.valueOf(num);
			}
		}
		else{
			if(num.equals("") == false){
				this.ypow = Integer.valueOf(num);
			}
		}
		return ;
	}
}
class TreeNodeComparator implements Comparator<express> {
	  @Override
	  
	  public int compare(express o1, express o2 )
	  {
		  if(o1.xpow < o2.xpow)return 1;
		  if(o1.xpow == o2.xpow && o1.xpow != 0){
			  if(o2.ypow == 0 && o1.ypow != 0)return 1;
			  if(o1.ypow < o2.ypow && o1.ypow != 0)return 1;
		  }
		  if(o1.xpow == o2.xpow && o1.xpow == 0 && o1.ypow < o2.ypow)return 1;
		  if(o1.xpow == o2.xpow && o1.ypow == o2.ypow)return 0;
		  return -1;
	  }
}
public class Main
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		int len;
		String conl;
		while(input.hasNext())
		{
			String[] strList;
			LinkedList<LinkedList<express>> list = new LinkedList<>();
			LinkedList<express> add = new LinkedList();
			
			len = Integer.valueOf(input.next());
			if(len == 0)return ;
			conl = input.next();
			for (int i = 0 ; i < len ; i ++)
			{
				String thisexp = input.next();
				strList = thisexp.split("\\+");
				list.add(new LinkedList<express>());
				for (String s : strList){
					list.get(i).add(new express(s));
				}
			}
			if(conl.equals("+"))
			{
				for (LinkedList<express> slist : list){
					express.plus(slist, add);
				}
			}
			else
			{
				express first = new express();
				first.flower = 1;
				first.xpow = 0;
				first.ypow = 0;
				add.add(first);
				for(LinkedList<express> slist : list)
				{
					add = express.product(slist, add);
				}
			}
			Collections.sort(add,new TreeNodeComparator());
			int first = 0;
			for(express node : add)
			{
				if(node.flower == 0)continue;
				if(first > 0)System.out.printf("+");
				node.printExpress();
				first += 1;
			}
			if(first == 0)System.out.printf("0");
			System.out.printf("\n");
		}
	}
}