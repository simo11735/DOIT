export default Vue.component("cerca", {
  props: {
    utente: {
      type: Object,
    },
  },
  /*html*/
  template: `
    <ion-content>
      <form @submit.prevent="search">
          <ion-item>
              <ion-select @ionChange="tipo = $event.target.value" placeholder="tipo">
              <ion-select-option value="progetti">progetti</ion-select-option>
              <ion-select-option value="proponente-progetti">proponente-progetti</ion-select-option>
              <ion-select-option value="progettisti">progettisti</ion-select-option>
              <ion-select-option value="esperti">esperti</ion-select-option>
              </ion-select>
          </ion-item>
          <ion-item>
              <ion-label>testo</ion-label>
              <ion-input @ionChange="testo = $event.target.value" placeholder="testo"></ion-input>
          </ion-item>
          <ion-button color="dark" expand="full" type="submit">cerca</ion-button>
      </form>
      <ion-list v-if="tipo === 'progetti'">
        <ion-card v-for="cercato in cercati" button @click="$router.push({path: '/progetto/'+cercato.id})">
          <ion-card-header>
            <ion-card-title>{{cercato.nome}}</ion-card-title>
            <ion-card-subtitle>{{cercato.competenza}}</ion-card-subtitle>
          </ion-card-header>
          <ion-card-content>
            <ion-card-description>{{cercato.descrizione}}</ion-card-description>
          <ion-card-content>
        </ion-card>
        </ion-list>
        <ion-list v-if="tipo === 'proponente-progetti'">
        <ion-card v-for="cercato in cercati" button @click="$router.push({path: '/proponente-progetto-profilo/'+cercato.id})">
          <ion-card-header>
            <ion-card-title>{{cercato.nome}} {{cercato.cognome}}</ion-card-title>
            <ion-card-subtitle>{{cercato.competenza}}</ion-card-subtitle>
          </ion-card-header>
        </ion-card>
        </ion-list>
        <ion-list v-if="tipo === 'progettisti'">
        <ion-card v-for="cercato in cercati" button @click="$router.push({path: '/progettista-profilo/'+cercato.id})">
          <ion-card-header>
            <ion-card-title>{{cercato.nome}} {{cercato.cognome}}</ion-card-title>
            <ion-card-subtitle>{{cercato.competenza}}</ion-card-subtitle>
          </ion-card-header>
        </ion-card>
        </ion-list>
        <ion-list v-if="tipo === 'esperti'">
        <ion-card v-for="cercato in cercati" button @click="$router.push({path: '/esperto-profilo/'+cercato.id})">
          <ion-card-header>
            <ion-card-title>{{cercato.nome}} {{cercato.cognome}}</ion-card-title>
            <ion-card-subtitle>{{cercato.competenza}}</ion-card-subtitle>
          </ion-card-header>
        </ion-card>
        </ion-list>
      </ion-content>
            `,
  data() {
    return {
      testo: "",
      tipo: "",
      cercati: [],
    };
  },
  methods: {
    async search() {
      if (this.testo === "" || this.tipo === "") {
        this.$emit("notifica", "campo mancante");
      } else {
        this.$emit("caricamento", true);
        this.cercati = await (
          await fetch("/" + this.tipo + "?nome=" + this.testo)
        ).json();
        this.$emit("caricamento", false);
      }
    },
  },
});
