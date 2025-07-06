import React, { useState } from "react";
import api from "../../services/api";

function TalkWithAi(){
    const [prompt, setPrompt] = useState('');
    const [response, setResponse] = useState('');

    const askAi = async () => {
        try {
            const response = await api.get('ask-ai-options', {
                params: {prompt}
            })
            const data = await response.data;
            console.log(data)
            setResponse(data);
            
        } catch (error) {
            console.log("Error generating response : ", error)
        }
    }

    return (
        <div>
            <h2>Perguntar para a IA</h2>
            <input type="text"
            value={prompt}
            onChange={(e) => setPrompt(e.target.value)}
            placeholder="Escreva o prompt para IA"></input>

            <button onClick={askAi}>Perguntar</button>

            <div className="output">
                <p>{response}</p>
            </div>
        </div>
    );

}

export default TalkWithAi;