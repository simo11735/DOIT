export default Vue.component("esperto-profilo", {
    props: {
      esperto: {
        type: Object,
      },
    },
    template: /*html*/ `
      <ion-content>
          <tool-bar titolo="esperto"></tool-bar>
          <ion-list>
              <ion-item>
                  <ion-label>nome</ion-label>    
                  <ion-title>{{esperto.nome}}</ion-title>
              </ion-item>
              <ion-item>
                  <ion-label>cognome</ion-label>    
                  <ion-title>{{esperto.cognome}}</ion-title>
              </ion-item>
              <ion-item>
                  <ion-label>competenza</ion-label>    
                  <ion-title>{{esperto.competenza}}</ion-title>
              </ion-item>
          </ion-list>
      </ion-content>
      `,
    data() {
      return {
        esperto: undefined,
      };
    },
    async created() {
      this.$emit("caricamento", true);
      this.esperto = await (
        await fetch("/esperto?id=" + this.$route.params.id)
      ).json();
      this.$emit("caricamento", false);
    },
  });
  