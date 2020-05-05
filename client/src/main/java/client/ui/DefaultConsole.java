package client.ui;

import java.io.IOException;
import java.util.Scanner;

public abstract class DefaultConsole {

    static final int ExitOption = 0;

    public void run() {
        int number=0;
        while (number!=-1) {
            displayMenu();
            int choice = readAnswer();
            try {
                number = dealChoice(choice);
            }
            catch (Exception exception)
            {
                System.out.println(exception.getMessage());
            }
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

    protected abstract int dealChoice(int choice) throws IOException;
    protected abstract void displayMenu();
}
