import { useState } from 'react';
import './App.css';
import TalkWithAi from './pages/chat/TalkWithAI';
import RecipeGenerator from './pages/recipe/RecipeGenerator';
import ImageGenerator from './pages/image/ImageGenerator';

function App() {

  const [activeTab, setActiveTab] = useState('ask-ai')
  const handleTabChange = (tab) => {
    setActiveTab(tab)
  }

  return (
    <div className="App">
      <button
      className={activeTab === 'ask-ai' ? 'active' : ''} 
      onClick={() => handleTabChange('ask-ai')}
      >
        Perguntar à IA
        </button>
      <button 
      className={activeTab === 'recipe-generator' ? 'active' : ''} 
      onClick={() => handleTabChange('recipe-generator')}
      >
        Gerar Receitas
        </button>
      <button       
      className={activeTab === 'image-generator' ? 'active' : ''} 
      onClick={() => handleTabChange('image-generator')}
      >
        Gerar Imagens
        </button>
        <div>
          {activeTab === 'ask-ai' && <TalkWithAi/>}
          {activeTab === 'recipe-generator' && <RecipeGenerator/>}
          {activeTab === 'image-generator' && <ImageGenerator/>}
        </div>

    </div>
  );
}

export default App;
