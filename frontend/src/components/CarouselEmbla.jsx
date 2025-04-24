import React, { useCallback, useEffect, useState } from 'react';
import useEmblaCarousel from 'embla-carousel-react';
import { Box, Paper, Typography, IconButton } from '@mui/material';
import { ArrowBackIos, ArrowForwardIos } from '@mui/icons-material';

const slides = [
    {
        title: 'Movie 1',
        description: 'An epic journey begins.',
        color: '#ffcc80'
    },
    {
        title: 'Movie 2',
        description: 'The adventure continues.',
        color: '#80deea'
    },
    {
        title: 'Movie 3',
        description: 'A stunning conclusion.',
        color: '#c5e1a5'
    }
];

const CarouselEmbla = () => {
    const [emblaRef, emblaApi] = useEmblaCarousel({ loop: true });
    const [canScrollPrev, setCanScrollPrev] = useState(false);
    const [canScrollNext, setCanScrollNext] = useState(false);

    const onSelect = useCallback(() => {
        if (!emblaApi) return;
        setCanScrollPrev(emblaApi.canScrollPrev());
        setCanScrollNext(emblaApi.canScrollNext());
    }, [emblaApi]);

    useEffect(() => {
        if (!emblaApi) return;
        emblaApi.on('select', onSelect);
        onSelect();
    }, [emblaApi, onSelect]);

    return (
        <Box position="relative" sx={{ overflow: 'hidden', width: '100%' }}>
            <Box ref={emblaRef} sx={{ display: 'flex' }}>
                <Box display="flex">
                    {slides.map((slide, index) => (
                        <Paper
                            key={index}
                            sx={{
                                flex: '0 0 100%',
                                height: 300,
                                margin: 1,
                                padding: 2,
                                backgroundColor: slide.color,
                                display: 'flex',
                                flexDirection: 'column',
                                justifyContent: 'center',
                                alignItems: 'center',
                            }}
                            elevation={4}
                        >
                            <Typography variant="h5">{slide.title}</Typography>
                            <Typography variant="body1">{slide.description}</Typography>
                        </Paper>
                    ))}
                </Box>
            </Box>

            <IconButton
                onClick={() => emblaApi && emblaApi.scrollPrev()}
                disabled={!canScrollPrev}
                sx={{ position: 'absolute', top: '50%', left: 8, transform: 'translateY(-50%)' }}
            >
                <ArrowBackIos />
            </IconButton>

            <IconButton
                onClick={() => emblaApi && emblaApi.scrollNext()}
                disabled={!canScrollNext}
                sx={{ position: 'absolute', top: '50%', right: 8, transform: 'translateY(-50%)' }}
            >
                <ArrowForwardIos />
            </IconButton>
        </Box>
    );
};

export default CarouselEmbla;
