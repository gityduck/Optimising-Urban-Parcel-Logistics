/* mainMenu.java runs the sub-main module 
 */

import java.util.*;

public class mainMenu 
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        int choice;

        while (running)
        {
            try
            {
                System.out.println();
				System.out.println("┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈・୨ ✦ ୧・┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈");
                System.out.println("\tCityDrop Logistics System");
                System.out.println("┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈");
                System.out.println("1 > Module 1: Route Planning Optimisation");
                System.out.println("2 > Module 2: Customer Lookup");
                System.out.println("3 > Module 3: Parcel Scheduling");
                System.out.println("4 > Module 4: Delivery Records");
                System.out.println("5 > Exit");
                System.out.println("┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈");

                System.out.print("Your choice: ");
				choice = sc.nextInt();	
				sc.nextLine();
				System.out.println();

                switch(choice)
                {
                    case 1:
                        Mod1_menu.runModule1();
                        break;
                    
                    case 2:
                        Mod2_menu.runModule2();
                        break;

                    case 3:
                        Mod3_menu.runModule3();
                        break;

                    case 4:
                        Mod4_menu.interativeMod4();
                        break;

                    case 5:
                        System.out.println("Exited");
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid input. Please insert 1, 2, 3, 4 or 5 only.");
                }
            }
            catch(InputMismatchException e)
			{
				System.out.println("Invalid input. Please enter a number from 1, 2, 3, 4 or 5 only.");
				sc.nextLine();
			}
        }
        sc.close();
    }
}