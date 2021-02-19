package geometry

import (
	"testing"

	"github.com/stretchr/testify/require"
)

func TestAdd(t *testing.T) {
	t.Run("addition operation", func(t *testing.T) {
		v1 := Vector{X: 1, Y: 2}
		v2 := Vector{X: 3, Y: 4}
		v3 := v1.Add(v2)

		require.Equal(t, Vector{X: 4, Y: 6}, v3)
	})
}
