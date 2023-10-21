package ru.netology.nKarskanov;

import ru.netology.nKarskanov.Excpetions.CustomerOperationOutOfBoundException;
import ru.netology.nKarskanov.domain.CashbackOperation;
import ru.netology.nKarskanov.domain.Client;
import ru.netology.nKarskanov.domain.LoanOperation;
import ru.netology.nKarskanov.domain.Operation;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static final int MAX_OPERATIONS = 50;
    public static  final  int MAX_CLIENTS = 5;

    private static final Operation[] OPERATIONS = new Operation[MAX_OPERATIONS];
    private static final Client[] CLIENTS = new Client[MAX_CLIENTS];

    private static final int[][] STATEMENT = new int[MAX_CLIENTS][MAX_OPERATIONS/MAX_CLIENTS];
    private static final int[] CLIENT_OPERATIONS_COUNT = new int[MAX_CLIENTS];

    public static void main(String[] args) throws CustomerOperationOutOfBoundException {

        Scanner scanner = new Scanner(System.in);

//        OPERATIONS[0] = new Operation(3, 300, "rub", "cafe");
//        OPERATIONS[1] = new CashbackOperation(4, 500, "rub", "bk", 50);
//        OPERATIONS[2] = new LoanOperation(5, 600, "rub", "mc", 1);
//        System.out.println(Arrays.toString(OPERATIONS));

        inputClients(scanner);
        inputOperations(scanner);
        getOperations(scanner);
    }

    private static void inputClients(Scanner scanner){
        int clientID = 0;
        while (true){
            System.out.println("Client name: ");
            String name = scanner.nextLine();
            Client client = new Client(clientID, name);

            CLIENTS[clientID] = client;
            clientID++;

            System.out.println("Do yo wont to enter next client? Y/N");
            String answer = scanner.nextLine();
            if(answer.equals("N")){
                break;
            }

            if(clientID == MAX_CLIENTS){
                break;
            }
        }
    }

    private static void inputOperations(Scanner scanner) throws CustomerOperationOutOfBoundException {
        int operationId = 0;
        while (true) {

            int sum;
            while (true){
                try {
                    System.out.println("Sum: ");
                    sum = scanner.nextInt();
                    scanner.nextLine();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Сумма должна быть задана числом");
                    scanner.next();
                }
            }


            System.out.println("Currency: ");
            String currency = scanner.nextLine();

            System.out.println("Merchant: ");
            String merchant = scanner.nextLine();

            Operation operation = new Operation(operationId, sum, currency, merchant);

            OPERATIONS[operationId] = operation;
            operationId++;

            int clientId;
            while (true){
                try {
                    System.out.println("ClientID: ");
                    clientId = scanner.nextInt();
                    scanner.nextLine();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Id клиента должно быть задано числом");
                    scanner.next();
                }
            }

            saveOperation(clientId, operationId);

            System.out.println("Do yo wont to enter next operation? Y/N");
            String answer = scanner.nextLine();
            if (answer.equals("N")) {
                break;
            }

            if (operationId == MAX_OPERATIONS) {
                break;
            }
        }
    }

    private static void saveOperation(int clientId, int operationId) throws CustomerOperationOutOfBoundException {
        int operationsCountForClient = CLIENT_OPERATIONS_COUNT[clientId];
        if (operationsCountForClient >= MAX_OPERATIONS/MAX_CLIENTS){
            throw new CustomerOperationOutOfBoundException(clientId, operationId);
        }
        STATEMENT[clientId][operationsCountForClient] = operationId;
        CLIENT_OPERATIONS_COUNT[clientId] = operationsCountForClient + 1;
    }

    public static void getOperations(Scanner scanner){
        while (true){
            System.out.println("Enter the client ID");
            int clientId = scanner.nextInt();
            scanner.nextLine();

            if (clientId >= MAX_CLIENTS || CLIENT_OPERATIONS_COUNT[clientId] == 0){
                System.out.println("Введён несуществующий ID\n");
                continue;
            }

            for (int i = 0; i < CLIENT_OPERATIONS_COUNT[clientId]; i++) {
                System.out.println(OPERATIONS[STATEMENT[clientId][i] - 1].toString());
            }

            System.out.println("Do yo wont to enter next clientID? Y/N");
            String answer = scanner.nextLine();
            if(answer.equals("N")){
                break;
            }
        }
    }
}