export default Vue.component("consiglia-progetto", {
    props: {
      utente: {
        type: Object,
      },
    },
    template: /*html*/ `
      <ion-content>
          <tool-bar titolo="messaggi riguardo i progetti"></tool-bar>
          <ion-list>
              <ion-item v-for="(messaggio, index) in messaggi" detail button @click="$router.push({path: '/messaggio/'+messaggio.id+'/Progetto'})">
                  <ion-label>{{messaggio.progetto.nome}}</ion-label>
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
        await fetch("/esperto/messaggiProgettista")
      ).json();
      this.$emit("caricamento", false);
    },
  });
  