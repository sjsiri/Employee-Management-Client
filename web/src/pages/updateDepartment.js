import EmployeeMgmtClient from '../api/employeeMgmtClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/**
 * Logic needed for the view department page of the website.
 */
//Global variable to track the very first employee Id retrieved and the first page.

class UpdateDepartment extends BindingClass {

    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'displayDeptDetails', 'update', 'redirectToViewDepartment'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

 /**
     * Once the client is loaded, get the employee details.
     */
    async clientLoaded() {
        const urlParams = new URLSearchParams(window.location.search);
        const departmentId = urlParams.get('id');
        this.dataStore.set('deptId', deptId);
        const employeeDetail = await this.client.getDepartment(departmentId);
        this.dataStore.set('departmentDetail', departmentDetail);
        this.displayDeptDetails();
    }

    /**
     * Add the header to the page and load the EmployeeMgmtClientClient.
     */
    async mount() {
        document.getElementById('save-department').addEventListener('click', this.update);
        this.header.addHeaderToPage();
        this.header.loadData();
        this.client = new EmployeeMgmtClient();
        await this.clientLoaded();

    }


 /**
     * Display department details  on the page.
     */
    displayDeptDetails() {
             const departmentDetail = this.dataStore.get('departmentDetail');

             if (!departmentDetail) {
                 return;
             }

         if (!departmentDetail) {
              return;
          }
          if (departmentDetail.deptId){
              document.getElementById('deptId').value = departmentDetail.deptId;
          }
          if (departmentDetail.deptName){
              document.getElementById('deptName').value = departmentDetail.deptName;
          }
          if (departmentDetail.deptStatus){
              document.getElementById('deptStatus').value = departmentDetail.deptStatus;
          }

    }

    async update() {

        const deptId = this.dataStore.get('deptId');
        const deptName = document.getElementById('deptName').value;
        const deptStatus = deptStatusBox.options[deptStatusBox.selectedIndex].innerHTML;
        document.getElementById('save-department').innerHTML = 'Saving Department...';

        const department = {
            deptId,
            deptName,
            deptStatus};

        const departmentUpdated = await this.client.updateDepartment(department);
        this.dataStore.set('department', departmentUpdated);
        this.redirectToViewDepartment();
    }

    redirectToViewDepartment() {
        const employee = this.dataStore.get('department');
        if (department) {
            window.location.href = `/departmentIndex.html`;
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const updateDepartment = new UpdateDepartment();
    await updateDepartment.mount();
};

window.addEventListener('DOMContentLoaded', main);