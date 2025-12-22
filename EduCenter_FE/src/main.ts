import { createApp } from "vue";
import App from "./App.vue";
import router from "./routers";
import "./assets/css/admin.css";

// Bootstrap
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap";

createApp(App).use(router).mount("#app");
