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
        const methodsToBind = ['clientLoaded', 'getIdentity', 'getEmployee', 'getAllEmployees', 'createEmployee', 'getAllDepartments'];
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
            const response = await this.client.get(`employee/${id}`);
            return response.data.singleEmployee;
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
         * Get all the departments on the list.
         * @param errorCallback (Optional) A function to execute if the call fails.
         * @returns The list of departments.
         */

     async getAllDepartments(errorCallback) {
            try {
                const response = await this.client.get(`departments`);
                return response.data.deptList;
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
    async createEmployee(firstName, lastName, jobTitle, email, phoneNumber, deptId,
        deptName, hireDate, dateOfBirth, employeeStatus, errorCallback) {
        try {
            const response = await this.client.post(`employees`, {
            firstName: firstName,
            lastName: lastName,
            jobTitle: jobTitle,
            email: email,
            deptId: deptId,
            deptName: deptName,
            hireDate: hireDate,
            phoneNumber: phoneNumber,
            dateOfBirth: dateOfBirth,
            employeeStatus: employeeStatus
            });
            return response.data.employeeModel;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async updateEmployee(employee, errorCallback) {
         try {
             const response = await this.client.put(`employees/${employee.employeeId}`, {
                 firstName: employee.firstName,
                 lastName: employee.lastName,
                 jobTitle: employee.jobTitle,
                 email: employee.email,
                 deptId: employee.deptId,
                 deptName: employee.deptName,
                 hireDate: employee.hireDate,
                 phoneNumber: employee.phoneNumber,
                 dateOfBirth: employee.dateOfBirth,
                 employeeStatus: employee.employeeStatus
             });
             return response.data.employeeModel;
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
