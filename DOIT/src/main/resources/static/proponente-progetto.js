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
            <ion-item detail button @click="$router.push({path: '/progetti-pp'})">
                <ion-label>lista progetti</ion-label>
            </ion-item>
            <ion-item detail button @click="$router.push({path: '/accetta-progettista'})">
                <ion-label>accetta progettista</ion-label>
            </ion-item>
            <ion-item detail button @click="$router.push({path: '/richiedi-consiglio-progettista'})">
                <ion-label>richiedi consiglio progettista</ion-label>
            </ion-item>
        </ion-list>
    </ion-content>
    `,
});