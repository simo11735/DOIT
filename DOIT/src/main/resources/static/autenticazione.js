export default Vue.component("autenticazione", {
  template: /*html*/ `
    <ion-content>
        <form @submit.prevent="autentica">
            <ion-list>
                <ion-item>
                    <ion-label>tipo</ion-label>
                    <ion-select value="proponente-progetto" @ionChange="tipo = $event.target.value" placeholder="tipo">
                    <ion-select-option value="proponente-progetto">proponente progetto</ion-select-option>
                    <ion-select-option value="progettista">progettista</ion-select-option>
                    <ion-select-option value="esperto">esperto</ion-select-option>
                    </ion-select>
                </ion-item>
                <ion-item>
                    <ion-label>username</ion-label>
                    <ion-input @ionChange="username = $event.target.value" placeholder="username" type="text"></ion-input>
                </ion-item>
                <ion-item>
                    <ion-label>password</ion-label>
                    <ion-input @ionChange="password = $event.target.value" placeholder="password" type="password"></ion-input>
                </ion-item>
            </ion-list>
            <ion-button expand='full' color="dark" type="submit">autentica</ion-button>
        </form>
    </ion-content>
    `,
  data() {
    return {
      username: "",
      password: "",
      tipo: "proponente-progetto",
    };
  },
  methods: {
    async autentica() {
      if (this.username === "" || this.password === "") {
        this.$emit("notifica", "campo mancante");
      } else {
        this.$emit("caricamento", true);
        let utente;
        try {
          switch (this.tipo) {
            case "proponente-progetto":
              utente = await (
                await fetch(
                  "autenticaProponenteProgetto?username=" +
                    this.username +
                    "&password=" +
                    this.password
                )
              ).json();
              break;
            case "progettista":
              utente = await (
                await fetch(
                  "autenticaProgettista?username=" +
                    this.username +
                    "&password=" +
                    this.password
                )
              ).json();
              break;
            case "esperto":
              utente = await (
                await fetch(
                  "autenticaEsperto?username=" +
                    this.username +
                    "&password=" +
                    this.password
                )
              ).json();
              break;
          }
          this.$emit("autentica", utente);
        } catch (e) {
          this.$emit("notifica", "errore di autenticazione");
        }
        this.$emit("caricamento", false);
      }
    },
  },
});
