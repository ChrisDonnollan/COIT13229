package client;/* Author Mary TomThis program demonstrates the use of GUI to collect data and send that to a server usinga client class.The Client class creates tcp connection to the server and sends the SalariedEmployee object.The Client class receives a list of SalariedEmployees. */import domain.SalariedEmployee;import java.net.*;import java.io.*;import java.util.LinkedList;public class EmployeeClient {    private Socket s = null;    private int serverPort;	   ObjectInputStream in;   ObjectOutputStream out;       private  LinkedList<SalariedEmployee> employees;// = new LinkedList<>();;    /**     *      * @throws IOException      * Constructor creates the socket, connects to server socket     * and sets the input and output streams connected to the socket.     */    public EmployeeClient () throws IOException{       employees = new LinkedList<>();       try {       serverPort = 8888;               s = new Socket("localhost", serverPort);        System.out.println(s.getInetAddress());        out =new ObjectOutputStream(s.getOutputStream());	in = new ObjectInputStream( s.getInputStream());        System.out.println("out "+ out.getClass()+ "in "+in.getClass());       } catch(IOException e) {       System.out.println("Connection:"+e.getMessage());     }    }   /**    * Method to send the data in the LinkedList to the server.    * Finishes by sending an object with a value of Firstname "finished".     * @param employees     */   public void sendEmployees(LinkedList<SalariedEmployee> employees) {//                 conn = new ServerConnection(employees);//                 employees.clear();        try{            out.writeObject("Employee");          while(employees.size()>0){              SalariedEmployee data = employees.removeFirst();              System.out.println(data);              out.writeObject(data);          }          if (employees.size() == 0)              out.writeObject(new SalariedEmployee("finished"));        } catch (IOException e) {                    System.err.println("IOException:  " + e.getMessage());      }         }       /**    *     * @return     * sends a string "view"    * server uses it to read file and get the employee list.    * The employee list is received from the server and stored in a LinkedList.    */    public LinkedList<SalariedEmployee> receiveEmployees() {             SalariedEmployee data;        try{            out.writeObject("view");             while (true){                data = (SalariedEmployee)in.readObject();                 if (data.getFirstName().equalsIgnoreCase("finished"))                   break;                                // System.out.println(data);              employees.add (new SalariedEmployee(data));//              data = (SalariedEmployee)in.readObject();              //System.out.println(employees);            }            System.out.println(employees);         return employees;             } catch (IOException e) {                    System.err.println("IOException:  " + e.getMessage());      } catch( ClassNotFoundException e){           System.out.println("IO:"+e.getMessage());        }        return null;    }       }