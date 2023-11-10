import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.sql.Timestamp;

public class ToDoList {
    static Scanner scan = new Scanner(System.in);
    static ArrayList<String> doneTask = new ArrayList<>();
    static ArrayList<String> removedTask = new ArrayList<>();
    static ArrayList<String> timeStampsArray = new ArrayList<>();
    static String[] toDoList;

    static int userChoice = 1;

    public static void main(String[] args) {
        //------------------------------------------------------------------//
        //Plano premium
        boolean premiumPlan = false;

        if (!premiumPlan) {
            System.out.println("\n\t\t\t\t\u001b[43;1m\u001b[38;5;15mIMPORTANT WARNING\u001b[0m\u001b[38;5;11m\nYou are currently using the Free Plan of ToDoList!\nYou can upgrade to Premium Plan in the upgrade menu!\u001b[0m");
            toDoList = new String[10];
        } else {
            toDoList = new String[30];
        }
        //------------------------------------------------------------------//

        while (userChoice != 0) {
            try {
                int count = 0;
                for (int i = 0; i < toDoList.length; i++) {
                    if (toDoList[i] == null) {
                        count++;
                    }
                }
                //------------------------------------------------------------------//
                //Menu Principal
                System.out.println("\n\u001b[38;5;15mYou still have \u001b[38;5;11m" + count + "\u001b[38;5;15m free spaces on the list!\u001b[0m");
                System.out.println();
                showMenu();
                userChoice = scan.nextInt();
                //------------------------------------------------------------------//
                //Opções Menu
                switch (userChoice) {
                    case 1:
                        showToDoList();
                        break;
                    case 2:
                        createTask();
                        break;
                    case 3:
                        markTaskAsCompleted();
                        break;
                    case 4:
                        removeTaskAsCompleted();
                        break;
                    case 5:
                        editTask();
                        break;
                    case 6:
                        deleteTask();
                        break;
                    case 7:
                        organizeAlphabetically();
                        break;
                    case 8:
                        premiumPlan = upgradeToDoListPlan( premiumPlan);
                        if (premiumPlan) {
                            String[] tempToDoList = new String[30];

                            for (int i = 0; i < toDoList.length; i++) {
                                if (i < tempToDoList.length) {
                                    tempToDoList[i] = toDoList[i];
                                } else {
                                    break;
                                }
                            }

                            toDoList = tempToDoList;
                        }
                        break;
                    case 9:
                        removeAllTaskCompleted();
                        break;
                    case 10:
                        recoverRemovedTask();
                        break;
                    case 11:
                        showTimeStamps();
                        break;
                    case 12:
                        organizeTask();
                        break;
                    case 0:
                        System.out.println("\n\u001b[38;5;9mClosing ToDoList program...\u001b[0m");
                        break;
                    default:
                        System.out.println("\n\u001b[38;5;9mInvalid option!\u001b[0m");
                        break;
                }
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Error");
                scan.next();
            }
        }

    }

    //------------------------------------------------------------------//
    public static void showMenu() {
        System.out.println("\u001b[38;5;15m 1 - Show ToDoList\u001b[0m");
        System.out.println("\u001b[38;5;15m 2 - Create task\u001b[0m");
        System.out.println("\u001b[38;5;15m 3 - Mark as completed\u001b[0m");
        System.out.println("\u001b[38;5;15m 4 - Remove as completed\u001b[0m");
        System.out.println("\u001b[38;5;15m 5 - Edit task\u001b[0m");
        System.out.println("\u001b[38;5;15m 6 - Delete task\u001b[0m");
        System.out.println("\u001b[38;5;15m 7 - Organize alphabetically\u001b[0m");
        System.out.println("\u001b[38;5;15m 8 - Upgrade ToDoList Plan\u001b[0m");
        System.out.println("+----------+");
        System.out.println("\u001b[38;5;15m 9 - Remove all completed\u001b[0m");
        System.out.println("\u001b[38;5;15m10 - Recover removed tasks \u001b[0m");
        System.out.println("\u001b[38;5;15m11 - Show time stamps\u001b[0m");
        System.out.println("\u001b[38;5;15m12 - Organize by done\u001b[0m");
        System.out.println("+----------+");
        System.out.println("\u001b[38;5;15m0 - Exit ToDoList\u001b[0m\n");
        System.out.print("\u001b[38;5;15mChoose a option: \u001b[0m");
    }

    //------------------------------------------------------------------//
    public static void showToDoList() {
        int count = 0;
        for (int i = 0; i < toDoList.length; i++) {
            if (toDoList[i] != null) {
                count++;
            }
        }
        if (count > 0) {
            System.out.println("\n\t\t\u001b[38;5;15mToDoList\u001b[0m");
            System.out.println("\u001b[38;5;8m------------------------\u001b[0m");
            for (int i = 0; i < toDoList.length; i++) {
                if (toDoList[i] != null) {
                    if (toDoList[i].contains(" ✅")) {
                        System.out.println("\u001b[38;5;7m" + i + ". \u001b[38;5;40m" + toDoList[i] + "\u001b[0m");
                    } else {
                        System.out.println("\u001b[38;5;7m" + i + ". \u001b[38;5;1m" + toDoList[i] + "\u001b[0m");
                    }
                }
            }
            System.out.println("\u001b[38;5;8m------------------------\u001b[0m");
        } else {
            System.out.println("\n\u001b[38;5;9mThe ToDoList is empty! You should create a task first.\u001b[0m");
        }
    }

    //------------------------------------------------------------------//
    public static void showPercentage() {
        System.out.println("Task Completion Percentage");
        double temp = 0;
        for (int i = 0; i < toDoList.length; i++) {
            if (toDoList[i] != null && toDoList[i].contains(" ✅")) {
                temp++;
            }
        }
        temp = temp / toDoList.length;
        System.out.println(temp + "%");
    }

    //------------------------------------------------------------------//
    public static void createTask() {
        System.out.print("\n\u001b[38;5;15mCreate task: \u001b[0m");
        scan.nextLine();
        String userNewTask = scan.nextLine().trim();
        if (!userNewTask.isEmpty()) {
            boolean added = false;
            for (int i = 0; i < toDoList.length; i++) {
                if (toDoList[i] == null) {
                    toDoList[i] = userNewTask;
                    System.out.println("\n\u001b[38;5;10mThe task '\u001b[38;5;15m" + userNewTask + "\u001b[38;5;10m' was created!\u001b[0m");
                    addTimeStamps("Task " + userNewTask + " created at: ");
                    creatNote(i);
                    added = true;
                    break;
                }
            }
            if (!added) {
                System.out.println("\n\n\u001b[38;5;9mThe list is full! You don't have more space.\u001b[0m");
            }
        } else {
            System.out.println("\n\n\u001b[38;5;9mTask name cannot be empty.\u001b[0m");
        }
    }

    //------------------------------------------------------------------//
    public static void creatNote(int num) {
        if (ToDoList.toDoList[num] != null) {
            System.out.println("Creat a note");
            System.out.println("[Y]es / [N]o");
            String choiceYN = scan.next().toLowerCase();

            if (choiceYN.equals("y")) {
                System.out.print("Insert note: ");
                scan.nextLine();
                String newNote = scan.nextLine();
                ToDoList.toDoList[num] += " Note:" + newNote;
            } else System.out.println("Continue...");
        } else {
            System.out.println("Impossible to create a note");
        }
    }

    //------------------------------------------------------------------//
    public static void markTaskAsCompleted() {
        int count = 0;
        for (int i = 0; i < toDoList.length; i++) {
            if (toDoList[i] != null) {
                System.out.println(i + " - " + toDoList[i]);
                count++;
            }
        }
        if (count != 0) {
            System.out.print("\n\u001b[38;5;15mChoose a task to mark as completed: \u001b[0m");
            int userChoiceOfTaskToMarkAsCompleted = scan.nextInt();

            if (toDoList[userChoiceOfTaskToMarkAsCompleted] != null) {
                if (toDoList[userChoiceOfTaskToMarkAsCompleted].contains(" ✅")) {
                    System.out.println("\n\u001b[38;5;9mThat task is already marked as completed!\u001b[0m");
                } else {
                    toDoList[userChoiceOfTaskToMarkAsCompleted] = toDoList[userChoiceOfTaskToMarkAsCompleted].concat(" ✅");
                    System.out.println("\n\u001b[38;5;10mTask successfuly marked as completed!\u001b[0m");
                    showPercentage();
                    addTimeStamps("Mark " + toDoList[userChoiceOfTaskToMarkAsCompleted] + "  as completed at: ");
                }
            } else {
                System.out.println("\n\u001b[38;5;9mInvalid task option!\u001b[0m");
            }
        } else {
            System.out.println("\n\u001b[38;5;9mYou don't have tasks!\u001b[0m");
        }
    }

    //------------------------------------------------------------------//
    public static void removeTaskAsCompleted() {
        int existsCompletedTasks = 0;
        for (int i = 0; i < toDoList.length; i++) {
            if (toDoList[i] != null && toDoList[i].contains(" ✅")) {
                System.out.println(i + " - " + toDoList[i]);
                existsCompletedTasks++;
            }
        }
        if (existsCompletedTasks > 0) {
            System.out.print("\n\u001b[38;5;15mChoose a task to remove as completed: \u001b[0m");
            int userChoiceOfTaskToRemoveAsCompleted = scan.nextInt();

            if (toDoList[userChoiceOfTaskToRemoveAsCompleted] != null) {
                toDoList[userChoiceOfTaskToRemoveAsCompleted] = toDoList[userChoiceOfTaskToRemoveAsCompleted].replace(" ✅", "");
            } else {
                System.out.println("\n\u001b[38;5;9mInvalid task option!\u001b[0m");
            }
        } else {
            System.out.println("\n\u001b[38;5;9mYou don't have completed tasks!\u001b[0m");
        }
    }

    //------------------------------------------------------------------//
    public static void removeAllTaskCompleted() {
        int counter = 0;
        for (int i = 0; i < toDoList.length; i++) {
            if (toDoList[i] != null && toDoList[i].contains(" ✅")) {
                String temp = toDoList[i];
                doneTask.add(counter, temp);
                removedTask.add(counter, temp);
                toDoList[i] = null;
                counter++;
            }
        }
        showToDoList();
    }

    //------------------------------------------------------------------//
    public static void recoverRemovedTask() {
        System.out.println("\"\\n\\u001b[38;5;15mChoose a task to recover: \\u001b[0m\"");
        for (int i = 0; i < removedTask.size(); i++) {
            System.out.println(i + "-" + removedTask.get(i));
        }
        System.out.print("Insert option: ");
        int optIn = scan.nextInt();
        int countSpace = 0;
        for (int i = 0; i < toDoList.length; i++) {
            if (toDoList[i] != null) {
                countSpace++;
            } else {
                toDoList[countSpace] = removedTask.get(optIn);
            }
        }
    }

    //------------------------------------------------------------------//
    public static void editTask() {
        int count = 0;
        for (int i = 0; i < toDoList.length; i++) {
            if (toDoList[i] != null) {
                System.out.println(i + " - " + toDoList[i]);
                count++;
            }
        }
        if (count != 0) {
            System.out.print("\n\u001b[38;5;15mChoose a task to edit: \u001b[0m");
            int userChoiceOfTaskToEdit = scan.nextInt();
            if (toDoList[userChoiceOfTaskToEdit] != null) {

                if (toDoList[userChoiceOfTaskToEdit].contains(" ✅")) {
                    toDoList[userChoiceOfTaskToEdit] = toDoList[userChoiceOfTaskToEdit].replace(" ✅", "");
                    System.out.println("\n\u001b[38;5;15mOld: " + toDoList[userChoiceOfTaskToEdit] + "\u001b[0m");
                    System.out.print("\u001b[38;5;15mNew: \u001b[0m");
                    scan.nextLine();
                    String userEditTask = scan.nextLine();

                    System.out.println("\n\u001b[38;5;10mThe task '\u001b[38;5;15m" + toDoList[userChoiceOfTaskToEdit] + "\u001b[38;5;10m' was changed to '\u001b[38;5;15m" + userEditTask + "\u001b[38;5;10m'!");
                    userEditTask = userEditTask.concat(" ✅");
                    toDoList[userChoiceOfTaskToEdit] = userEditTask;

                } else {
                    System.out.println("\n\u001b[38;5;15mOld: " + toDoList[userChoiceOfTaskToEdit] + "\u001b[0m");
                    System.out.print("\u001b[38;5;15mNew: \u001b[0m");
                    scan.nextLine();
                    String userEditTask = scan.nextLine();
                    System.out.println("\n\u001b[38;5;10mThe task '\u001b[38;5;15m" + toDoList[userChoiceOfTaskToEdit] + "\u001b[38;5;10m' was changed to '\u001b[38;5;15m" + userEditTask + "\u001b[38;5;10m'!");
                    toDoList[userChoiceOfTaskToEdit] = userEditTask;
                    addTimeStamps("Task" + toDoList[userChoiceOfTaskToEdit] + " edited at: ");
                }
            } else {
                System.out.println("\n\u001b[38;5;9mInvalid task option!\u001b[0m");
            }
        } else {
            System.out.println("\n\u001b[38;5;9mYou don't have tasks to edit!\u001b[0m");
        }
    }

    //------------------------------------------------------------------//
    public static void deleteTask() {
        int existedTasks = 0;
        for (int i = 0; i < toDoList.length; i++) {
            if (toDoList[i] != null) {
                System.out.println(i + " - " + toDoList[i]);
                existedTasks++;
            }
        }
        if (existedTasks > 0) {
            System.out.print("\n\u001b[38;5;15mChoose a task to delete: \u001b[0m");
            int userChoiceOfTaskToDelete = scan.nextInt();

            if (toDoList[userChoiceOfTaskToDelete] != null) {
                System.out.println("\u001b[38;5;10mThe task '\u001b[38;5;15m" + toDoList[userChoiceOfTaskToDelete] + "\u001b[38;5;10m' was successfully deleted!\u001b[0m");
                removedTask.add(toDoList[userChoiceOfTaskToDelete]);
                toDoList[userChoiceOfTaskToDelete] = null;
                addTimeStamps("Deleted " + toDoList[userChoiceOfTaskToDelete] + " task at: ");
            } else {
                System.out.println("\u001b[38;5;9mInvalid task option!\u001b[0m");
            }
        } else {
            System.out.println("\u001b[38;5;9mYou don't have tasks to delete!\u001b[0m");
        }
    }

    //------------------------------------------------------------------//
    public static void organizeTask() {
        int count = 0;
        for (int i = 0; i < toDoList.length; i++) {
            for (int j = 0; j < toDoList.length - i - 1; j++) {
                if (toDoList[i] != null && toDoList[i].contains(" ✅")) {
                    String temp = toDoList[j];
                    toDoList[j] = toDoList[j + 1];
                    toDoList[j + 1] = temp;
                    count++;
                }
            }
        }
        Arrays.sort(toDoList, 0, count);
        showToDoList();
    }

    //------------------------------------------------------------------//
    public static void organizeAlphabetically() {
        int count = 0;
        for (int i = 0; i < toDoList.length; i++) {
            if (toDoList[i] != null) {
                count++;
            }
        }
        Arrays.sort(toDoList, 0, count);
        showToDoList();
    }

    //------------------------------------------------------------------//
    public static boolean upgradeToDoListPlan( boolean premium) {
        if (!premium) {
            System.out.println("\n\u001b[38;5;15mDo you want to buy Premium Plan? (yes or no)\u001b[0m");
            System.out.print("\u001b[38;5;15m> \u001b[0m");
            String userUpgradeOption = scan.next();

            switch (userUpgradeOption) {
                case "yes":
                    premium = true;
                    System.out.println("\n\u001b[38;5;10mCurrently plan setted to Premium! Thank you!\u001b[0m");
                    break;
                default:
                    premium = false;
                    System.out.println("\n\u001b[38;5;12mMaybe next time then...\u001b[0m");
                    break;
            }
            return premium;
        } else {
            System.out.println("\n\u001b[38;5;11mYour plan is already setted to Premium! You don't need to buy it again.\u001b[0m");
        }
        return premium;
    }

    //------------------------------------------------------------------//
    public static void addTimeStamps(String type) {
        String timeStampsString;
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        timeStampsString = currentTimestamp.toString();
        //System.out.println(timeStampsString);
        timeStampsArray.add(type + " " + timeStampsString);
    }

    //------------------------------------------------------------------//
    public static void showTimeStamps() {
        for (int i = 0; i < timeStampsArray.size(); i++) {
            System.out.println(timeStampsArray.get(i));
        }
    }
    //------------------------------------------------------------------//
}