import React, { useState } from "react";
import api from "../../services/api";
import ReactMarkdown from 'react-markdown';

function RecipeGenerator(){
        const [ingredients, setIngredients] = useState('');
        const [cuisine, setCuisine] = useState('');
        const [dietaryRestriction, setDietaryRestriction] = useState('');
        const [recipe, setRecipe] = useState('');

        const createRecipe = async () => {
             try {
            const response = await api.get('recipe-creator', {
                params: {ingredients, dietaryRestriction, cuisine}
            })
            const data = await response.data;
            console.log(data)
            setRecipe(data);
            
        } catch (error) {
            console.log("Error generating recipe : ", error)
        }
        }
        

    return (
        <div>
            <h2>Gerar Receitas</h2>
             <div className="inputs-container">
            <input type="text"
            value={ingredients}
            onChange={(e) => setIngredients(e.target.value)}
            placeholder="Escreva os ingredientes (separados por vírgula)"></input>

            <input type="text"
            value={cuisine}
            onChange={(e) => setCuisine(e.target.value)}
            placeholder="Escreva o tipo de cozinha"></input>

            <input type="text"
            value={dietaryRestriction}
            onChange={(e) => setDietaryRestriction(e.target.value)}
            placeholder="Escreva o tipo de restrição de dieta"></input>
            
            <button onClick={createRecipe}>Gerar Receita</button>

            <div className="output">
                <ReactMarkdown>{recipe}</ReactMarkdown>
            </div>
            </div>
        </div>
    );

}

export default RecipeGenerator;