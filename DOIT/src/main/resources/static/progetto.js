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
            <ion-item v-for="(progettista, index) in progetto.progettisti" detail button @click="$router.push({path: '/progettista-profilo/'+progettista.id})">
                <ion-label>{{progettista.nome}} {{progettista.cognome}}</ion-label>
            </ion-item>
        </ion-list>
        <ion-list v-if="utente && progetto.candidature.length > 0 && utente.tipo === 'proponente-progetto'">
            <ion-title>candidature</ion-title>
            <ion-item v-for="(progettista, index) in progetto.candidature">
                <ion-label>{{progettista.nome}} {{progettista.cognome}}</ion-label>
                <ion-button color="dark" @click="$router.push({path: '/progettista-profilo/'+progettista.id})">profilo</ion-button>
                <ion-button color="dark" @click="accettaCandidatura(index)">
                    accetta
                </ion-button>
            </ion-item>
        </ion-list>
        <ion-list v-if="utente && utente.tipo === 'progettista'">
          <ion-item>
              <ion-title>funzionalità progettista</ion-title>
          </ion-item>
          <ion-item v-if="!progetto.candidature.map(c => c.id).includes(utente.id) && !progetto.progettisti.map(p => p.id).includes(utente.id)" 
            button @click="candidati">
            candidati
          </ion-item>
          <ion-item button detail @click="$router.push({path: '/esperti-richiesta/'+progetto.id})">
            richiedi consiglio
          </ion-item>
        </ion-list>
        <ion-list v-if="utente && utente.tipo === 'proponente-progetto'">
          <ion-item>
              <ion-title>funzionalità proponente progetto</ion-title>
          </ion-item>
          <ion-item detail button @click="$router.push({path: '/progettisti-richiesta/'+progetto.id})">
              <ion-label>richiedi consiglio per progettista</ion-label>
          </ion-item>
        </ion-list>
    </ion-content>
    `,
  data() {
    return {
      progetto: undefined,
    };
  },
  async created() {
    this.aggiorna();
  },
  methods: {
    async aggiorna() {
      this.$emit("caricamento", true);
      await this.$emit('aggiorna');
      this.progetto = await (
        await fetch("/progetto?id=" + this.$route.params.id)
      ).json();
      this.$emit("caricamento", false);
    },
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
      await this.aggiorna();
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
      await this.aggiorna();
      if (status >= 200 && status < 300) this.$emit("notifica", "successo");
      else this.$emit("notifica", "errore nella candidatura");
      this.$emit("caricamento", false);
    },
  },
});
