export default Vue.component("progetto", {
  props: {
    utente: {
      type: Object,
    },
  },
  template: /*html*/ `
    <ion-content>
        <tool-bar titolo="progetto"></tool-bar>
        <ion-list>
            <ion-item>
                <ion-label>nome</ion-label>    
                <ion-title>{{progetto.nome}}</ion-title>
            </ion-item>
            <ion-item>
                <ion-label>descrizione</ion-label>    
                <ion-title>{{progetto.descrizione}}</ion-title>
            </ion-item>
            <ion-item>
                <ion-label>competenza</ion-label>    
                <ion-title>{{progetto.competenza}}</ion-title>
            </ion-item>
            <ion-item>
                <ion-label>proponente progetto</ion-label>    
                <ion-title>{{progetto.proponenteProgetto.nome}} {{progetto.proponenteProgetto.cognome}}</ion-title>
            </ion-item>
        </ion-list>
        <ion-list v-if="progetto.progettisti.length > 0">
            <ion-title>progettisti</ion-title>
            <ion-item v-for="(progettista, index) in progetto.progettisti">
                <ion-label>{{progettista.nome}} {{progettista.cognome}}</ion-label>
            </ion-item>
        </ion-list>
        <ion-list v-if="progetto.candidature.length > 0">
            <ion-title>candidature</ion-title>
            <ion-item v-for="(progettista, index) in progetto.candidature">
                <ion-label>{{progettista.nome}} {{progettista.cognome}}</ion-label>
                <ion-button v-if="utente.tipo === 'proponente-progetto'" color="dark" @click="accettaCandidatura(index)">
                    accetta
                </ion-button>
            </ion-item>
        </ion-list>
        <ion-button v-if="utente.tipo === 'progettista' && !progetto.candidature.map(c => c.id).includes(utente.id) && !progetto.progettisti.map(p => p.id).includes(utente.id)" 
        expand='full' color="dark" @click="candidati">candidati</ion-button>
    </ion-content>
    `,
  data() {
    return {
      progetto: undefined,
    };
  },
  async created() {
    this.$emit("caricamento", true);
    this.progetto = await (
      await fetch("/progetto?id=" + this.$route.params.id)
    ).json();
    this.$emit("caricamento", false);
  },
  methods: {
    async accettaCandidatura(index) {
      this.$emit("caricamento", true);
      const status = (
        await fetch(
          "/proponenteProgetto/accettaCandidatura?idProgetto=" +
            this.progetto.id +
            "&idProgettista=" +
            this.progetto.candidature[index].id,
          { method: "POST" }
        )
      ).status;
      this.$emit('aggiorna');
      if (status >= 200 && status < 300) this.$emit("notifica", "successo");
      else this.$emit("notifica", "errore nell'accettazione");
      this.$emit("caricamento", false);
    },
    async candidati() {
      this.$emit("caricamento", true);
      const status = (
        await fetch(
          "progettista/creaCandidatura?idProgetto=" + this.progetto.id,
          { method: "POST" }
        )
      ).status;
      this.$emit('aggiorna');
      if (status >= 200 && status < 300) this.$emit("notifica", "successo");
      else this.$emit("notifica", "errore nella candidatura");
      this.$emit("caricamento", false);
    },
  },
});