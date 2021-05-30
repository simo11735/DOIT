export default Vue.component("proponente-progetto-profilo", {
    props: {
      proponenteprogetto: {
        type: Object,
      },
    },
    template: /*html*/ `
      <ion-content>
          <tool-bar titolo="proponenteprogetto"></tool-bar>
          <ion-list>
              <ion-item>
                  <ion-label>nome</ion-label>    
                  <ion-title>{{proponenteprogetto.nome}}</ion-title>
              </ion-item>
              <ion-item>
                  <ion-label>cognome</ion-label>    
                  <ion-title>{{proponenteprogetto.cognome}}</ion-title>
              </ion-item>
          </ion-list>
          <ion-list v-if="proponenteprogetto.progetti.length > 0">
              <ion-title>progetti</ion-title>
              <ion-item v-for="(progetto, index) in proponenteprogetto.progetti" detail button @click="$router.push({path: '/progetto/'+progetto.id})">
                  <ion-label>{{progetto.nome}} {{progetto.cognome}}</ion-label>
              </ion-item>
          </ion-list>
      </ion-content>
      `,
    data() {
      return {
        proponenteprogetto: undefined,
      };
    },
    async created() {
      this.$emit("caricamento", true);
      this.proponenteprogetto = await (
        await fetch("/proponente-progetto?id=" + this.$route.params.id)
      ).json();
      this.$emit("caricamento", false);
    },
  });
  