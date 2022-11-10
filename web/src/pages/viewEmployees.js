/**
 * Logic needed for the view playlist page of the website.
 */
class ViewEmployees extends BindingClass {
    constructor() {
        super();
        //TODO
        this.bindClassMethods(['clientLoaded', 'mount', 'addPlaylistToPage', 'addSongsToPage', 'addSong'], this);
        this.dataStore = new DataStore();
        //this.dataStore.addChangeListener(this.addPlaylistToPage);
        //this.dataStore.addChangeListener(this.addSongsToPage);
        this.header = new Header(this.dataStore);
        console.log("viewemployees constructor");
    }

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const viewEmployees = new ViewEmployees();
    viewEmployees.mount();
};

window.addEventListener('DOMContentLoaded', main);