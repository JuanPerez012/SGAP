<template>
  <div>
    <h1>Gestión de Archivos Peligrosos</h1>

    <input type="file" @change="handleFile" />
    <select v-model="nivelRiesgo">
      <option>ALTO</option>
      <option>MEDIO</option>
      <option>BAJO</option>
    </select>

    <button @click="subir">Subir</button>

    <ul>
      <li v-for="a in archivos" :key="a.id">
        {{ a.nombre }} - {{ a.nivelRiesgo }} - {{ a.hash }}
      </li>
    </ul>
  </div>
</template>

<script>
import { subirArchivo, obtenerArchivos } from "../services/api";

export default {
  data() {
    return {
      archivo: null,
      nivelRiesgo: "MEDIO",
      archivos: []
    };
  },
  methods: {
    handleFile(e) {
      this.archivo = e.target.files[0];
    },
    async subir() {
      const formData = new FormData();
      formData.append("file", this.archivo);
      formData.append("nivelRiesgo", this.nivelRiesgo);

      await subirArchivo(formData);
      this.cargar();
    },
    async cargar() {
      const res = await obtenerArchivos();
      this.archivos = res.data;
    }
  },
  mounted() {
    this.cargar();
  }
};
</script>