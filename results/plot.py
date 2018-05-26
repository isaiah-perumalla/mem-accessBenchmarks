import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
from matplotlib.ticker import MaxNLocator
from collections import namedtuple


def get_data_frame(fname, name):
    df = pd.read_csv(fname)
    stripped_names = df.Benchmark.str.split('.', n=None, expand=True)
    df['name'] = stripped_names[stripped_names.columns[-1]]
    return df[df.name.str.endswith(name)]

df = get_data_frame(r'./page-align-x86.csv', 'L1-dcache-loads')
n_groups = len(set(df['Param: pages']))

pageAligned_df = df[df['Param: isPageAligned'] == True]
notPageAligned_df = df[df['Param: isPageAligned'] == False]
means_men = list(pageAligned_df.Score)
std_men = list(pageAligned_df['Score Error (99.9%)'])

means_women = list(notPageAligned_df.Score)
std_women = list(notPageAligned_df['Score Error (99.9%)'])

fig, ax = plt.subplots()

index = np.arange(n_groups)
bar_width = 0.35

opacity = 0.4
error_config = {'ecolor': '0.3'}

rects1 = ax.bar(index, means_men, bar_width,
                alpha=opacity, color='b',
                yerr=std_men, error_kw=error_config,
                label='page-aligned')

rects2 = ax.bar(index + bar_width, means_women, bar_width,
                alpha=opacity, color='r',
                yerr=std_women, error_kw=error_config,
                label='unaligned')

ax.set_xlabel('working set (pages 4k)')
ax.set_ylabel('microseconds per Op')
ax.set_title('Ops by page aligned vs unaligned')
ax.set_xticks(index + bar_width / 2)
pages = list(df['Param: pages'])
ax.set_xticklabels(pages)
ax.legend()

fig.tight_layout()
plt.show()
