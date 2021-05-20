export default Vue.component('esperto', {
    props: {
        utente: {
            type: Object
        }
    },
    template:
    /*html*/`
    <ion-content>
        <ion-list>
            <ion-item detail button @click="$router.push({path: '/consiglia-progettista'})">
                <ion-label>consiglia progettista</ion-label>
            </ion-item>
            <ion-item detail button @click="$router.push({path: '/consiglia-progetto'})">
                <ion-label>consiglia progetto</ion-label>
            </ion-item>
        </ion-list>
    </ion-content>
    `
});