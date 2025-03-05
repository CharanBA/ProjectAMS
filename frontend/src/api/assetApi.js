import api from './index';

export const getAllAssets = async () => {
  try {
    const response = await api.get('/api/asset/all');
    return response.data.reverse();
  } catch (error) {
    throw error;
  }
};

export const addAsset = async (assetData) => {
  try {
    await api.post('/api/asset', assetData);
  } catch (error) {
    throw error;
  }
};


export const getAssetById = async (id) => {
  try {
    const response = await api.get(`/api/asset?id=${id}`);
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const assignComponentToAsset = async (assetId, componentId) => {
    try {
      const response = await api.post(`/api/asset/${assetId}/component/${componentId}`);
      return response.data;
    } catch (error) {
      throw error;
    }
  };

