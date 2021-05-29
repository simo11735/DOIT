export default Vue.component("progettista", {
  props: {
    utente: {
      type: Object,
    },
  },
  template: /*html*/ `
    <ion-content>
        <ion-list>
            <ion-item detail button @click="$router.push({path: '/progetti/progettista'})">
                <ion-label>lista progetti</ion-label>
            </ion-item>
            <ion-item detail button @click="$router.push({path: '/invia-candidatura'})">
                <ion-label>invia candidatura</ion-label>
            </ion-item>
            <ion-item detail button @click="$router.push({path: '/aggiungi-esperienza'})">
                <ion-label>aggiungi esperienza</ion-label>
            </ion-item>
            <ion-item detail button @click="$router.push({path: '/richiedi-consiglio-progetto'})">
                <ion-label>richiedi consiglio progetto</ion-label>
            </ion-item>
        </ion-list>
    </ion-content>
    `,
});
