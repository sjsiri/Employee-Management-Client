import axios from "axios";
import BindingClass from "../util/bindingClass";

/**
 * Client to call the EmployeeMgmtClient.
 *
 * This could be a great place to explore Mixins. Currently the client is being loaded multiple times on each page,
 * which we could avoid using inheritance or Mixins.
 * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Classes#Mix-ins
 * https://javascript.info/mixins
  */
export default class EmployeeMgmtClient extends BindingClass {

    constructor(props = {}){
        super();
        const methodsToBind = ['clientLoaded', 'getIdentity', 'getEmployee', 'getAllEmployees', 'createEmployee'];
        this.bindClassMethods(methodsToBind, this);
        this.props = props;

        axios.defaults.baseURL = INVOKE_URL;
        this.client = axios;
        this.clientLoaded(this.client);
    }

    /**
     * Run any functions that are supposed to be called once the client has loaded successfully.
     * @param client The client that has been successfully loaded.
     */
    clientLoaded(client) {
        if (this.props.hasOwnProperty("onReady")){
            this.props.onReady();
        }
    }

    /**
     * Get the identity of the current user
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The username and phonetool url for the current user.
     */
    async getIdentity(errorCallback) {
        try {
            // TODO auth?
            //const response = await this.client.get(`identity`);
            const data = {'username': 'Nashville Software'};
            //return response.data;
            return data;
        } catch(error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Gets the employee info for the given ID.
     * @param id Unique identifier for an employee
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The employee's data.
     */
    async getEmployee(id, errorCallback) {
        try {
            const response = await this.client.get(`employees/${id}`);
            return response.data.employee;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Get all the employees on the list.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The list of employees.
     */
    async getAllEmployees(startEmployee, navDirection , errorCallback) {
        try {
            const response = await this.client.get(`employees/${startEmployee}/${navDirection}`);
            console.log("In GetAllEmployees method. Response value:", response);
            return response.data.employeeList;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Create a new employee contact.
     * @param name The name of the playlist to create.
     * @param customerId The user who is the owner of the playlist.
     * @param tags Metadata tags to associate with a playlist.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The playlist that has been created.
     */
    async createEmployee(fname, lname, jobtitle, email, phone, dept, dob, errorCallback) {
        try {
            const response = await this.client.post(`employees`, {
            //TODO: Add the post attributes per the API definition. Check with Jack
                name: name,
                customerId: customerId,
                tags: tags
            });
            return response.data.employee;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }



    /**
     * Helper method to log the error and run any error functions.
     * @param error The error received from the server.
     * @param errorCallback (Optional) A function to execute if the call fails.
     */
    handleError(error, errorCallback) {
        console.error(error);
        if (error.response.data.message !== undefined) {
            console.error(error.response.data.message)
        }
        if (errorCallback) {
            errorCallback(error);
        }
    }
}
