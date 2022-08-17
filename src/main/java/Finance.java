import com.h2.BestLoanRates;
import com.h2.MortgageCalculator;
import com.h2.SavingsCalculator;

import java.util.Arrays;
import java.util.Map;

public class Finance {

    public static final String BEST_LOAN_RATES = "bestLoanRates";
    public static final String SAVINGS_CALCULATOR = "savingsCalculator";
    public static final String MORTGAGE_CALCULATOR = "mortgageCalculator";

    public static final Map<String, String> commandsToUsage = Map.of(BEST_LOAN_RATES, "usage: bestLoanRates",
                                                                    SAVINGS_CALCULATOR, "usage: savingsCalculator <credits separated by ','> <debits separated by ','>",
                                                                    MORTGAGE_CALCULATOR, "usage: mortgageCalculator <loanAmount> <termInYears> <annualRate>");
    private static boolean validateCommandArguments(String[] args) {
        switch (args[0]) {
            case BEST_LOAN_RATES:
                if (args.length == 1)
                    return true;
            case SAVINGS_CALCULATOR:
                if (args.length == 3)
                    return true;
            case MORTGAGE_CALCULATOR:
                if (args.length == 4)
                    return true;
        }
        return false;
    }

    private static void executeCommand(String command, String[] args) {
        switch (command) {
            case BEST_LOAN_RATES:
                System.out.println("Finding best loan rates ...");
                BestLoanRates.main(args);
                return;
            case SAVINGS_CALCULATOR:
                System.out.println("Finding your net savings ...");
                SavingsCalculator.main(args);
                return;
            case MORTGAGE_CALCULATOR:
                System.out.println("Finding your monthly payment ...");
                MortgageCalculator.main(args);
        }
    }

    public static void main(String[] args) {
        String command = args[0];
        if (!commandsToUsage.containsKey(command)) {
            System.out.println(command + ": command not found");
            return;
        }

        boolean isValidCommand = validateCommandArguments(args);
        if (!isValidCommand) {
            System.out.println(commandsToUsage.get(command));
            return;
        }
        executeCommand(command, Arrays.copyOfRange(args, 1, args.length));
    }
}
