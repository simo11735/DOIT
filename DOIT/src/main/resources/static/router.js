import("./toolbar.js");

const routes = [
  { path: "/autenticazione", component: () => import("./autenticazione.js") },
  { path: "/registrazione", component: () => import("./registrazione.js") },
  { path: "/utente", component: () => import("./utente.js") },
  {
    path: "/proponente-progetto",
    component: () => import("./proponente-progetto.js"),
  },
  { path: "/esperto", component: () => import("./esperto.js") },
  { path: "/progettista", component: () => import("./progettista.js") },
  {
    path: "/proposta-progetto",
    component: () => import("./proposta-progetto.js"),
  },
  {
    path: "/progetti/:tipo",
    component: () => import("./progetti.js"),
  },
  {
    path: "/invia-candidatura",
    component: () => import("./invia-candidatura.js"),
  },
  {
    path: "/accetta-candidatura",
    component: () => import("./accetta-candidatura.js"),
  },
  {
    path: "/progetto/:id",
    component: () => import("./progetto.js"),
  },
  {
    path: "/progettisti-richiesta/:idProgetto",
    component: () => import("./progettisti-richiesta.js"),
  },
  {
    path: '/esperti-richiesta/:idProgetto/:idProgettista?',
    component: () => import("./esperti-richiesta.js"),
  },
  {
    path: '/consiglia-progetto',
    component: () => import("./consiglia-progetto.js"),
  },
  {
    path: '/consiglia-progettista',
    component: () => import("./consiglia-progettista.js"),
  },
  {
    path: '/messaggio/:id/:tipo',
    component: () => import("./messaggio.js"),
  },
  {
    path: '/messaggi',
    component: () => import("./messaggi.js"),
  },
  {
    path: '/aggiungi-esperienza',
    component: () => import("./aggiungi-esperienza.js"),
  },
  {
    path: '/cerca',
    component: () => import("./cerca.js"),
  },
  {
    path: '/proponente-progetto-profilo/:id',
    component: () => import("./proponente-progetto-profilo.js"),
  },
  {
    path: '/progettista-profilo/:id',
    component: () => import("./progettista-profilo.js"),
  },
  {
    path: '/esperto-profilo/:id',
    component: () => import("./esperto-profilo.js"),
  },
  {
    path: "/",
    redirect: "/autenticazione",
  },
];

new Vue({
  router: new VueRouter({ routes }),
  data() {
    return {
      utente: undefined,
      loading: undefined,
    };
  },
  methods: {
    async aggiorna() {
      this.utente = await (await fetch('/'+this.utente.tipo+'?id='+this.utente.id)).json();
    },
    autentica(utente) {
      this.utente = utente;
      document.cookie = "id=" + utente.id;
      this.$router.replace({ path: "/utente" });
    },
    esci() {
      this.utente = undefined;
      this.$router.replace({ path: "/autenticazione" });
    },
    caricamento(flag) {
      if (flag && !this.loading) {
        this.loading = document.createElement("ion-loading");
        this.loading.cssClass = "caricamento";
        this.loading.message = "caricamento...";
        document.body.appendChild(this.loading);
        this.loading.present();
      } else {
        this.loading.dismiss();
      }
    },
    notifica(testo) {
      const toast = document.createElement("ion-toast");
      toast.message = testo;
      toast.duration = 2000;
      document.body.appendChild(toast);
      return toast.present();
    },
  },
}).$mount("#app");
