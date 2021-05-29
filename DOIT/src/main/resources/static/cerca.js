export default Vue.component("cerca", {
  props: {
    utente: {
      type: Object,
    },
  },
  /*html*/
  template: `
    <ion-content>
    <ion-list>
        <ion-item>
            <ion-select @ionChange="tipo = $event.target.value" placeholder="tipo" value="progetto">
            <ion-select-option value="progetto">progetto</ion-select-option>
            <ion-select-option value="proponente-progetto">proponente-progetto</ion-select-option>
            <ion-select-option value="cercato">cercato</ion-select-option>
            <ion-select-option value="esperto">esperto</ion-select-option>
            </ion-select>
        </ion-item>
        <ion-item>
            <ion-label>testo</ion-label>
            <ion-input @ionChange="testo = $event.target.value" placeholder="testo"></ion-input>
        </ion-item>
        <ion-button color="dark" expand="full" @click="search">cerca</ion-button>
    </ion-list>
        <ion-card v-if="tipo === 'progetto'" v-for="cercato in cercati" button @click="$router.push({path: '/progetto/'+cercato.id">
          <ion-card-header>
            <ion-card-title>{{cercato.nome}}</ion-card-title>
            <ion-card-subtitle>{{cercato.competenza}}</ion-card-subtitle>
          </ion-card-header>
          <ion-card-content>
            <ion-card-description>{{cercato.descrizione}}</ion-card-description>
          <ion-card-content>
        </ion-card>
        <ion-card v-if="tipo !== 'progetto'" v-for="cercato in cercati" button @click="$router.push({path: '/'+tipo+'/'+cercato.id})">
          <ion-card-header>
            <ion-card-title>{{cercato.nome}} {{cercato.cognome}}</ion-card-title>
            <ion-card-subtitle>{{cercato.competenza}}</ion-card-subtitle>
          </ion-card-header>
        </ion-card>
      </ion-content>
            `,
  data() {
    return {
      tipo: "progetto",
      cercati: [],
    };
  },
  methods: {
    search() {
      this.$emit("caricamento", true);
      let tipo = '';
      switch(this.tipo) {
          case 'proponente-progetto': tipo = 'proponente-progetti'; break;
          case 'progetto': tipo = 'progetti'; break;
          case 'progettista': tipo = 'progettisti'; break;
          case 'esperto': tipo = 'esperti'; break;
      }
      this.cercati = await(
        await fetch("/" + tipo + "?nome=" + this.testo)
      ).json();
      this.$emit("caricamento", false);
    },
  },
});
