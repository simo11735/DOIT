export default Vue.component("proponente-progetto", {
  props: {
    utente: {
      type: Object,
    },
  },
  template: /*html*/ `
    <ion-content>
        <ion-list>
            <ion-item detail button @click="$router.push({path: '/proposta-progetto'})">
                <ion-label>proposta progetto</ion-label>
            </ion-item>
            <ion-item detail button @click="$router.push({path: '/progetti/proponenteProgetto'})">
                <ion-label>lista progetti</ion-label>
            </ion-item>
            <ion-item detail button @click="$router.push({path: '/messaggi'})">
                <ion-label>messaggi</ion-label>
            </ion-item>
        </ion-list>
    </ion-content>
    `,
});