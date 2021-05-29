export default Vue.component("consiglia-progettista", {
    props: {
      utente: {
        type: Object,
      },
    },
    template: /*html*/ `
      <ion-content>
          <tool-bar titolo="messaggi riguardo i progettisti"></tool-bar>
          <ion-list>
              <ion-item v-for="(messaggio, index) in messaggi" detail button @click="$router.push({path: '/messaggio/'+messaggio.id+'/Progettista'})">
                  <ion-label>{{messaggio.progettista.nome}} {{messaggio.progettista.cognome}}</ion-label>
              </ion-item>
          </ion-list>
      </ion-content>
      `,
    data() {
      return {
        messaggi: [],
      };
    },
    async created() {
      this.$emit("caricamento", true);
      this.messaggi = await (
        await fetch("/esperto/messaggiProponenteProgetto")
      ).json();
      this.$emit("caricamento", false);
    },
  });
  