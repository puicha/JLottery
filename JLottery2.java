/* JLottery2 application
   A lottery game application.  Using check boxes that the user will choose six numbers display in each check box.
   Generate six random number, each between 0 and 30, inclusive.  After the player has chosen six numbers, display
   the randomly selected numbers, the player's numbers, and the amount of money the user has won. In addition, add a menu bar
   to the program with a File menu and a submenu called About that display a JOptionPane message contains my name,my course,
   the section number, and MEID. */
   
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class JLottery2 extends JFrame implements ActionListener
{
   // Declare variables and named constant
   int count = 0;
   int matchCount;
   int numInt;
   final int NUM = 31;
   final int MIN_NUM = 0;
   final int MAX_NUM = 30;
   final int ALLOW = 6;
   String msg = "";
   String ranMsg = "";
   String numString = "";
   
   // Create array of random numbers and array of numbers picked
   int[] numPickArray = new int[6];
   int[] ranNumArray = new int[6];  
     
   // Instantiate JMenuBar, JMenu, JMenuItem and JLabel objects
   JMenuBar mainMenu = new JMenuBar();
   JMenu file = new JMenu("File");
   JMenuItem about = new JMenuItem("About"); 
   JLabel instruction = new JLabel("Pick Six Numbers");
   JLabel numPick = new JLabel("");
   JLabel numRand = new JLabel("");
   JLabel award = new JLabel("");
   
   /* Declare an array of objects in which each object in the array become the item
      of each JCheckBox. */
   String[] numArray = {"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16",
                        "17","18","19","20","21","22","23","24","25","26","27","28","29","30"};
                        
   // Instantiate JCheckBox array objects                     
   JCheckBox[] checkBoxes = new JCheckBox[31];
   
   // Create the JLottery2 class constructor
   public JLottery2()
   {
      // Include instructions to set the title by passing it to the JFrame parent class constructor
      super("Lottery Game");
      
      // Set the JFrame's layout manager to FlowLayout
      setLayout(new FlowLayout());

      // Set the default close operation for the JFrame
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      /* Add the JMenuBar to the JFrame using the setJMenuBar() method and use add() method to add JMenu to JMenuBar
         and JMenuItem to JMenu. Add actionListener to about JMenuItem. */
      setJMenuBar(mainMenu);
      mainMenu.add(file);
      file.add(about);     
      about.addActionListener(this);
      
      /* Add JCheckBox array to the JFrame using for loop in which each JCheckBox display the String number from 
         numArray array. Add ActionListener to each JCheckbox. */
      for(int i = 0; i < NUM; ++i)
      {
         checkBoxes[i] = new JCheckBox(numArray[i]);
         add(checkBoxes[i]);
         checkBoxes[i].addActionListener(this);
      }
      
      // Set fonts for instruction JLabel and add to the JFrame
      instruction.setFont(new Font("Arial", Font.BOLD, 22));
      add(instruction);     
   }
   
   // Create the actionPerformed() method
   public void actionPerformed(ActionEvent e)
   {  
      Object source = e.getSource(); 
      
      // Let the user picks the number from the checkboxes, the user can only pick 6 times
      if(count < ALLOW)
      {
         // Use for loop to go through the array of JCheckBox to check if the user checked the check box 
         for(int i = 0; i < NUM; ++i)
         {
            if(source == checkBoxes[i])
            {
               // Call getText() method to get text from JCheckBox at that index.  Assign text to numString variable
               numString = checkBoxes[i].getText();
               
               // Concatenate the numString variable and assign it to msg variable
               msg += "   " + numString;
               
               // Call setText() method to assign text concatenated in msg to numPick JLabel
               numPick.setText("The numbers you selected are " + msg);  
               
               // Convert String to integer and assign it to numInt variable            
               numInt = Integer.parseInt(numString);
               
               // Assign numInt to numPickArray array
               numPickArray[count] = numInt;  
            }                   
         }
         // If count equals to 5 (count starts from 0, so count at 5 is the sixth time that user selected the numbers)
         if(count == 5)
         {  
            // Add numPick JLabel to the JFrame           
            add(numPick);
            
            // Generate six random numbers and assign to the ranNumArray array
            for(int y = 0; y < ALLOW; ++y)
            {
               ranNumArray[y] = MIN_NUM + (int)(Math.random() * MAX_NUM);
               
               // Concatenate the numbers in ranNumArray array and assign to ranMsg variable
               ranMsg += "   " + ranNumArray[y];
               
               // Call setText() method to assign text concatenated in msg to numPick JLabel
               numRand.setText("The random numbers are" + ranMsg);
               
               // Add numRand JLabel to the JFrame
               add(numRand);
            }
            /* Call compareNumbers() method that accepts arguments of two arrays and return value of count for the numbers that
               are matched. */
            matchCount = compareNumbers(numPickArray,ranNumArray); 
            
            // Call determineAward() method that accepts matchCount argument
            determineAward(matchCount);                          
         }                             
      }
      // If count more than or equal to 6 and the user does not select about JMenuItem
      else if(count >= ALLOW && source != about) 
      {  
         // Disable JCheckBoxes using for loop
         for(int i = 0; i < NUM; ++i)
         {
            checkBoxes[i].setEnabled(false);
         }
         // Display the message using JOptionPane
         JOptionPane.showMessageDialog(null,"Cannot pick more than six numbers!");
      }
      
      // If the user selects about JMenuItems
      if(source == about)
      {  
         // Display the message of my information using JOptionPane
         JOptionPane.showMessageDialog(null,"Chonlada Morse, CIS 263AA Section number 36454 MEID CHO2060527");
         
         // Decrement count, so this count will not be added with the count when the user selects the check boxes
         count--;
      }
      // Increment count when the user selects the check boxes
      count++;  
   }
   
   // Create compareNumbers() method that accepts two parameters of two array and return int value of match count
   public int compareNumbers(int[] pArray, int[] rArray)
   {
      // Declare variable
      int mCount = 0;
      
      //Compare picked numbers in the array with random numbers in the array using for nested loop
      for(int a = 0; a < pArray.length; ++a)
      {
          for(int b = 0; b < rArray.length; ++b)
          {
              if(pArray[a]==rArray[b])
              {
                  mCount++;
              }              
          }           
      }
      // return the result
      return mCount;   
   }
   
   // Create determineAward() method to display the amount of money the user has won
   public void determineAward(int mCount)
   {
      // Use switch staments and setText() method to assign award.  Add award JLabel to the JFrame 
      switch(mCount)
      {  
         case 0:
         case 1:
         case 2:
            award.setText("$0 Award, Try again next time");
            add(award);
            break;
         case 3:
            award.setText("Congratulation! You've won $100 Award");
            add(award);
            break;
         case 4:
            award.setText("Congratulation! You've won $10,000 Award");    
            add(award);
            break;
         case 5:
            award.setText("Congratulation! You've won $50,000 Award");
            add(award);
            break;
         case 6:
            award.setText("Congratulation! You've won $1,000,000 Award");
            add(award);
            break;         
       }           
   }
   
   // Add a main method that instantiates a JLottery2 object and sets its size and visibility  
   public static void main(String[] args)
   {      
      // Declare named constants 
      final int WIDTH = 400;
      final int HEIGHT = 500;
      
      // Instantiate a JLottery2 object
      JLottery2 lottery = new JLottery2();
      lottery.setSize(WIDTH, HEIGHT);
      lottery.setVisible(true); 
   }   
}
