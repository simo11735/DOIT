export default Vue.component("registrazione", {
  template: /*html*/ `
    <ion-content>
        <form @submit.prevent="registra()">
            <ion-list>
                <ion-item>
                    <ion-label>nome</ion-label>
                    <ion-input @ionChange="nome = $event.target.value" placeholder="nome" type="text"></ion-input>
                </ion-item>
                <ion-item>
                    <ion-label>cognome</ion-label>
                    <ion-input @ionChange="cognome = $event.target.value" placeholder="cognome" type="text"></ion-input>
                </ion-item>
                <ion-item>
                    <ion-label>tipo</ion-label>
                    <ion-select value="proponente-progetto" @ionChange="tipo = $event.target.value" placeholder="tipo">
                    <ion-select-option value="proponente-progetto">proponente progetto</ion-select-option>
                    <ion-select-option value="progettista">progettista</ion-select-option>
                    <ion-select-option value="esperto">esperto</ion-select-option>
                    </ion-select>
                </ion-item>
                <ion-item v-if="this.tipo !== '' && this.tipo !== 'proponente-progetto'">
                    <ion-label>competenza</ion-label>
                    <ion-select @ionChange="competenza = $event.target.value" placeholder="competenza">
                    <ion-select-option v-for="competenza in competenze" :value="competenza">{{competenza}}</ion-select-option>
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
                <ion-item>
                    <ion-label>ripeti password</ion-label>
                    <ion-input @ionChange="ripetipassword = $event.target.value" placeholder="ripeti password" type="password"></ion-input>
                </ion-item>
            </ion-list>
            <ion-button expand='full' color="dark" type="submit">registra</ion-button>
        </form>
    </ion-content>
    `,
  data() {
    return {
      nome: "",
      cognome: "",
      competenze: [],
      competenza: "",
      tipo: "proponente-progetto",
      username: "",
      password: "",
      ripetipassword: "",
    };
  },
  async created() {
    this.competenze = await (await fetch("competenze")).json();
  },
  methods: {
    async registra() {
      if (
        this.nome === "" ||
        this.cognome === "" ||
        (this.tipo !== "proponente-progetto" && this.competenza === "") ||
        this.username === "" ||
        this.password === ""
      ) {
        this.$emit("notifica", "campo mancante");
      } else if (this.password === this.ripetipassword) {
        this.$emit("caricamento", true);
        let status;
        switch (this.tipo) {
          case "proponente-progetto":
            status = (
              await fetch(
                "registraProponenteProgetto?nome=" +
                  this.nome +
                  "&cognome=" +
                  this.cognome +
                  "&username=" +
                  this.username +
                  "&password=" +
                  this.password,
                { method: "POST" }
              )
            ).status;
            break;
          case "progettista":
            status = (
              await fetch(
                "registraProgettista?nome=" +
                  this.nome +
                  "&cognome=" +
                  this.cognome +
                  "&competenza=" +
                  this.competenza +
                  "&username=" +
                  this.username +
                  "&password=" +
                  this.password,
                { method: "POST" }
              )
            ).status;
            break;
          case "esperto":
            status = (
              await fetch(
                "registraEsperto?nome=" +
                  this.nome +
                  "&cognome=" +
                  this.cognome +
                  "&competenza=" +
                  this.competenza +
                  "&username=" +
                  this.username +
                  "&password=" +
                  this.password,
                { method: "POST" }
              )
            ).status;
            break;
        }
        if (status >= 200 && status < 300) this.$emit("notifica", "successo");
        else this.$emit("notifica", "errore");
        this.$emit("caricamento", false);
      } else {
        this.$emit("notifica", "password differenti");
      }
    },
  },
});
