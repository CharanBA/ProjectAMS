import api from './index';

export const getComponentsByAssetId = async (assetId) => {
  try {
    const response = await api.get(`/api/component/asset/${assetId}/components`);
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const addComponent = async (componentData) => {
  try {
    await api.post('/api/component', componentData);
  } catch (error) {
    throw error;
  }
};

export const getAllComponents = async () => {
  try {
    const response = await api.get('/api/component/all');
    return response.data;
  } catch (error) {
    throw error;
  }
};


