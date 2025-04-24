import React, { useEffect, useState } from 'react';
import axios from '../../api/axiosConfig'; // Using axios with interceptors
import Hero from '../hero/Hero';

const Home = () => {
  const [movies, setMovies] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // Fetch movies from the backend when the component mounts
  useEffect(() => {
    const fetchMovies = async () => {
      try {
        const response = await axios.get('/api/v1/movies');
        setMovies(response.data); // Store fetched data in state
      } catch (err) {
        setError('Failed to fetch movies. Please try again later.');
        console.error(err); // Log error for debugging
      } finally {
        setLoading(false); // Stop loading after the request is completed
      }
    };

    fetchMovies(); // Call the fetch function
  }, []); // Empty dependency array to run this effect only once on mount

  if (loading) {
    return <div>Loading...</div>; // Simple loading message or spinner
  }

  if (error) {
    return <div>{error}</div>; // Display error message if fetching fails
  }

  return (
    <div>
      <Hero movies={movies} /> {/* Pass the movies prop to the Hero component */}
    </div>
  );
};

export default Home;
