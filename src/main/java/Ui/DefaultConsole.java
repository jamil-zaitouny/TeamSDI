package Ui;

import java.util.Scanner;

public abstract class DefaultConsole {

    static final int ExitOption = 0;

    public void run() {
        while (true) {
            displayMenu();
            int choice = readAnswer();
            int number=0;
            try {
                number = dealChoice(choice);
            }
            catch (Exception exception)
            {
                System.out.println(exception.getMessage());
            }
            if(number == -1) break;
        }
    }

    private int readAnswer()
    {
        Scanner scanIn = new Scanner(System.in);
        try{
            return Integer.parseInt(scanIn.nextLine());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    protected abstract int dealChoice(int choice);
    protected abstract void displayMenu();
}
