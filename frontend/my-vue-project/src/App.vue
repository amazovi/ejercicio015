<template>
  <div id="app">
    <h1>Bienvenidos al Mundo Animal</h1>
    <p>Esta aplicación muestra una lista de animales desde la base de datos.</p>
    <button @click="loadAnimals">Cargar Listado</button>
    <div v-if="animals.length > 0">
      <ul>
        <li v-for="animal in animals" :key="animal.id">
          {{ animal.name }} ({{ animal.species }})
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue';

export default {
  setup() {
    const animals = ref([]);

    const loadAnimals = async () => {
      try {
        const response = await fetch('http://localhost:8080/api/animals'); // Cambia la URL según tu configuración
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        animals.value = await response.json();
      } catch (error) {
        console.error('Error fetching animals:', error);
      }
    };

    return { animals, loadAnimals };
  }
}
</script>

<style scoped>
#app {
  text-align: center;
  padding: 20px;
  max-width: 600px;
  margin: auto;
}

h1 {
  color: #42b983;
}

button {
  background-color: #42b983;
  color: white;
  border: none;
  padding: 10px 20px;
  cursor: pointer;
  font-size: 16px;
  border-radius: 5px;
}

button:hover {
  background-color: #35a076;
}
</style>