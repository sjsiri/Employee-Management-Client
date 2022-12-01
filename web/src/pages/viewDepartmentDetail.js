import EmployeeMgmtClient from '../api/employeeMgmtClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/**
 * Logic needed for the view department page of the website.
 */
//Global variable to track the very first department Id retrieved and the first page.

class ViewDepartmentDetail extends BindingClass {

    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'displayDeptDetails' ], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.displayDeptDetails);
        this.header = new Header(this.dataStore);
    }

 /**
     * Once the client is loaded, get the department details.
     */
    async clientLoaded() {
        const urlParams = new URLSearchParams(window.location.search);
        const deptId = urlParams.get('id');
        document.getElementById('dept_loading').innerHTML = "(Loading department details...)";

        const departmentDetail = await this.client.getDepartment(deptId);
        this.dataStore.set('departmentDetail', departmentDetail);
        document.getElementById('update-department').addEventListener('click', async evt => {
                          console.log('The element that was clicked was ', departmentId);
                            window.location.href = `/update_department.html?id=${deptId}`;
                          });

        document.getElementById('dept_loading').innerHTML = "";
    }

    /**
     * Add the header to the page and load the EmployeeMgmtClientClient.
     */
    async mount() {
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
        if (departmentDetail.deptId){
            document.getElementById('deptId').innerHTML = departmentDetail.deptId;
        }
        if (departmentDetail.deptName){
            document.getElementById('deptName').innerHTML = departmentDetail.deptName;
        }
        if (departmentDetail.deptStatus){
            document.getElementById('deptStatus').innerHTML = departmentDetail.deptStatus;
        }

 }


}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const viewDepartmentDetail = new ViewDepartmentDetail();
    await viewDepartmentDetail.mount();
};

window.addEventListener('DOMContentLoaded', main);